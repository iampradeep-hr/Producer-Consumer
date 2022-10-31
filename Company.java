class Company{

    int n;
    boolean flag=false;
    //flag-false (Producer will produce)
    //flag-true  (Consumer will consume)
    public synchronized void produce_item(int n){
        if(flag){
            try { wait(); } catch (Exception e) {}
        }
        this.n=n;
        System.out.println("Produced :"+n);
        flag=!flag;
        notify();
    }

    public synchronized int consume_item(){
        if(!flag){
            try { wait(); } catch (Exception e) {}
        }
        System.out.println("Consumed :"+n);
        flag=!flag;
        notify();
        return n;
    }
}