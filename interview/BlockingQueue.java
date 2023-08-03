package interview;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private Queue<Integer> q;
    private int capacity;

    public BlockingQueue(int capacity) {
        q = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int item) {
        synchronized (q) {
            while (q.size() == capacity) {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            q.add(item);
            q.notifyAll();
            return true;
        }
    }

    public int remove() {
        synchronized (q) {
            while (q.size() == 0) {
                try {
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int temp = q.poll();
            q.notifyAll();
            return temp;
        }
    }


}


class BlockingQueueTester {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue(5);

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Producing: " + i);
                blockingQueue.add(i);
                try {
                    Thread.sleep(1000); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int item = blockingQueue.remove();
                System.out.println("Consuming: " + item);
                try {
                    Thread.sleep(1500); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

