class Company{

    int n;

    public synchronized void produce_item(int n){
        this.n=n;
        System.out.println("Produced :"+n);
    }

    public synchronized int consume_item(){
        System.out.println("Consumed :"+n);
        return n;
    }
}