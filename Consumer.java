public class Consumer extends Thread {

    private Company c;
    Consumer(Company c){
        this.c=c;
    }

    @Override
    public void run() {
        while(true){
            c.consume_item();
            try {Thread.sleep(2000);} catch (Exception e) {}
        }
    }
    
}
