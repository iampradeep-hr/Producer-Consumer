package example;

public class Producer extends Thread {

    private Company c;

    Producer(Company c){
        this.c=c;
    }

    @Override
    public void run() {
        int i=1;
        while(true){
            c.produce_item(i);
            try {Thread.sleep(1000);} catch (Exception e) {}
            i++;
        }
    }
    
}
