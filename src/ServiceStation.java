public class ServiceStation {
    private Queue<Passenger> queue;
    private Passenger passenger;
    private int serviceTime;
    private int service_num;

    public ServiceStation(){
        queue = new Queue<>();
        serviceTime = 0;
        service_num = 1;
    }
    public void addServiceTime(int time){
        this.serviceTime += time;
    }
    public int getServiceTime(){
        return serviceTime;
    }

    //policy 1
    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
    }
    public Passenger getPassenger(){
        return passenger;
    }
    public boolean isFree(){
        return passenger == null;
    }

    //policy 2
    public void addPassenger(Passenger passenger){
        queue.enqueue(passenger);
        service_num++;
    }
    public void removePassenger(){
        queue.dequeue();
    }
    public Passenger getCurrentPassenger(){
        return queue.peek();
    }
    public int getPassengerNum(){
        return queue.getSize();
    }
    public int getServiceNum(){
        return service_num;
    }
    public boolean isEmpty(){
        return queue.empty();
    }
}
