public class Simulation1 {
    private int D; //simulation duration
    private int A; //average arrival rate measured in minutes
    private int S; //service rate
    private int time = 0;
    private int passenger_num = 0;

    private Queue<Passenger> passengerQueue;

    private ServiceStation ss1, ss2, ss3, ss4, ss5;
    private int max_queue_length = 0;
    private int max_waiting_time = 0;
    private int waiting_sum = 0;

    public Simulation1(int duration, int arrive_rate, int service_rate) {
        //initialize simulation parameters
        D = duration;
        A = arrive_rate;
        S = service_rate;

        passengerQueue = new Queue<>();
        ss1 = new ServiceStation();
        ss2 = new ServiceStation();
        ss3 = new ServiceStation();
        ss4 = new ServiceStation();
        ss5 = new ServiceStation();
    }
    private double rand() {
        return Math.random();
    }

    private int RandNat(int N) {
        return (int) ((N + 1) * rand());
    }

    private int RandPos(int N) {
        return (int) (1 + N * rand());
    }

    private int RandInt(int A, int B) {
        return A + RandNat(B - A);
    }

    private int RandAvg(int A) {
        return RandInt(1, 2 * A - 1);
    }

    private boolean RandEvent(int N) {
        return (rand() < (1.0 / N));
    }

    private boolean newpassenger(){
        return  RandEvent(A) &&  time <= D;
    }
    private boolean ongoingsimulation(){
        return !(time > D
                && passengerQueue.empty()
                && ss1.isFree()
                && ss2.isFree()
                && ss3.isFree()
                && ss4.isFree()
                && ss5.isFree()
        );
    }

    private void createPassenger(){
        //add passenger to passenger queue
        passenger_num++;
        Passenger passenger = new Passenger();
        passenger.setId(passenger_num);
        passenger.setArriveTime(time);
        passenger.setServiceTime(RandAvg(S));
        //new passenger is added to queue
        passengerQueue.enqueue(passenger);
        //get max queue length
        if(max_queue_length < passengerQueue.getSize()){
            max_queue_length = passengerQueue.getSize();
        }
        System.out.println("New passenger " + passenger_num + " is arrived at " + passenger.getArriveTime() + " min");
        System.out.println(" passenger "+passenger_num+ "Service time: " + passenger.getServiceTime() + " min");

    }
    private void updateServiceStation(ServiceStation ss){
        //update max queue length
        if(!ss.isFree()){
            Passenger current_passenger = ss.getPassenger();
            //if passenger received service, leave station
            if(current_passenger.getStartTime() + current_passenger.getServiceTime() <= time){
                ss.setPassenger(null);
                System.out.println("Passenger " + current_passenger.getId() + " received service and left station at " + time + " min");
                ss.addServiceTime(current_passenger.getServiceTime());
            }
        }
    }

    //start simulation
    public void start(){
        while (ongoingsimulation()){
            if(newpassenger()){
                createPassenger();
            }
            //find empty service station and dispatch passenger
            if(!passengerQueue.empty()){
                Passenger passenger = null;
                if(ss1.isFree()){
                    passenger = passengerQueue.dequeue();
                    ss1.setPassenger(passenger);
                    System.out.println("Service Station 1 take " + "Passenger " + passenger.getId() + " at " + time);
                } else if(ss2.isFree()){
                    passenger = passengerQueue.dequeue();
                    ss2.setPassenger(passenger);
                    System.out.println("Service Station 2 take " + "Passenger " + passenger.getId() + " at " + time);
                } else if(ss3.isFree()){
                    passenger = passengerQueue.dequeue();
                    ss3.setPassenger(passenger);
                    System.out.println("Service Station 3 take " + "Passenger " + passenger.getId() + " at " + time);
                } else if(ss4.isFree()){
                    passenger = passengerQueue.dequeue();
                    ss4.setPassenger(passenger);
                    System.out.println("Service Station 4 take " + "Passenger " + passenger.getId() + " at " + time);
                } else if(ss5.isFree()){
                    passenger = passengerQueue.dequeue();
                    ss5.setPassenger(passenger);
                    System.out.println("Service Station 5 take " + "Passenger " + passenger.getId() + " at " + time);
                }
                if(passenger != null){
                    passenger.setStartTime(time);
                    int waiting_time = time - passenger.getArriveTime();
                    if(max_waiting_time < waiting_time){
                        max_waiting_time = waiting_time;
                    }
                    waiting_sum += waiting_time;
                }
            }

            //update all passenger
            updateServiceStation(ss1);
            updateServiceStation(ss2);
            updateServiceStation(ss3);
            updateServiceStation(ss4);
            updateServiceStation(ss5);
            time++;
        }

        //print result
        System.out.println("------------------------------------------------");
        System.out.println("Duration of simulation: " + time + " min");
        System.out.println("Maximum length of the queue: " + max_queue_length);
        System.out.println("Maximum waiting of the queue: " + max_waiting_time + " min");
        int avg_wait = waiting_sum / passenger_num;
        System.out.println("Average waiting: " + avg_wait + " min");
        int rate_1 = (ss1.getServiceTime()) * 100 / time;
        int rate_2 = (ss2.getServiceTime()) * 100 / time;
        int rate_3 = (ss3.getServiceTime()) * 100 / time;
        int rate_4 = (ss4.getServiceTime()) * 100 / time;
        int rate_5 = (ss5.getServiceTime()) * 100 / time;

        System.out.println("Rate of occupancy of station 1: " + rate_1 + " %");
        System.out.println("Rate of occupancy of station 2: " + rate_2 + " %");
        System.out.println("Rate of occupancy of station 3: " + rate_3 + " %");
        System.out.println("Rate of occupancy of station 4: " + rate_4 + " %");
        System.out.println("Rate of occupancy of station 5: " + rate_5 + " %");
    }
}
