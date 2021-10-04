public class Passenger {
    private int id;
    private int arriveTime;
    private int startTime;
    private int serviceTime;

    public void setId(int id){
        this.id = id;
    }
    public void setArriveTime(int arriveTime){
        this.arriveTime = arriveTime;
    }
    public void setStartTime(int startTime){
        this.startTime = startTime;
    }
    public void setServiceTime(int serviceTime){
        this.serviceTime = serviceTime;
    }
    public int getId(){
        return id;
    }
    public int getArriveTime(){
        return arriveTime;
    }
    public int getStartTime(){
        return startTime;
    }
    public int getServiceTime(){
        return serviceTime;
    }
}
