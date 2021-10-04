public class Simulation2 {
    private int D; //simulation duration
    private int A; //average arrival rate measured in minutes
    private int S; //service rate
    private int time = 0;
    private int passenger_num = 0;

    private ServiceStation ss1, ss2, ss3, ss4, ss5;

    private int max_queue_length_1 = 0;
    private int max_queue_length_2 = 0;
    private int max_queue_length_3 = 0;
    private int max_queue_length_4 = 0;
    private int max_queue_length_5 = 0;

    private int max_waiting_time_1 = 0;
    private int max_waiting_time_2 = 0;
    private int max_waiting_time_3 = 0;
    private int max_waiting_time_4 = 0;
    private int max_waiting_time_5 = 0;

    private int waiting_sum_1 = 0;
    private int waiting_sum_2 = 0;
    private int waiting_sum_3 = 0;
    private int waiting_sum_4 = 0;
    private int waiting_sum_5 = 0;

    private int order = 1;

    public Simulation2(int duration, int arrive_rate, int service_rate){
        D = duration;
        A = arrive_rate;
        S = service_rate;
        ss1 = new ServiceStation();
        ss2 = new ServiceStation();
        ss3 = new ServiceStation();
        ss4 = new ServiceStation();
        ss5 = new ServiceStation();
    }
    public void initialize() {
        time = 0;
        passenger_num = 0;
        ss1 = new ServiceStation();
        ss2 = new ServiceStation();
        ss3 = new ServiceStation();
        ss4 = new ServiceStation();
        ss5 = new ServiceStation();
        max_queue_length_1 = 0;
        max_queue_length_2 = 0;
        max_queue_length_3 = 0;
        max_queue_length_4 = 0;
        max_queue_length_5 = 0;

        max_waiting_time_1 = 0;
        max_waiting_time_2 = 0;
        max_waiting_time_3 = 0;
        max_waiting_time_4 = 0;
        max_waiting_time_5 = 0;

        waiting_sum_1 = 0;
        waiting_sum_2 = 0;
        waiting_sum_3 = 0;
        waiting_sum_4 = 0;
        waiting_sum_5 = 0;

        order = 1;
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
                && ss1.isEmpty()
                && ss2.isEmpty()
                && ss3.isEmpty()
                && ss4.isEmpty()
                && ss5.isEmpty());
    }

    private void createPassengerWithA(){
        //add passenger to service station as rule A
        passenger_num++;
        Passenger passenger = new Passenger();
        passenger.setId(passenger_num);
        passenger.setArriveTime(time);
        passenger.setServiceTime(RandAvg(S));

        String station_name = "Service Station 1";
        ServiceStation selected_station = ss1;

        if(order == 1){
            order = 2;
        } else if(order == 2){
            selected_station = ss2;
            station_name = "Service Station 2";
            order = 3;
        } else if(order == 3){
            selected_station = ss3;
            station_name = "Service Station 3";
            order = 4;
        } else if(order == 4){
            selected_station = ss4;
            station_name = "Service Station 4";
            order = 5;
        } else if(order == 5){
            selected_station = ss5;
            station_name = "Service Station 5";
            order = 1;
        }
        if(selected_station.isEmpty()) {
            passenger.setStartTime(time);
        }
        selected_station.addPassenger(passenger);
        System.out.println("New passenger " + passenger_num + " is arrived at " + passenger.getArriveTime() + " min");
        System.out.println("Service time: " + passenger.getServiceTime() + " min");
        System.out.println("Passenger " + passenger_num + " is dispatched to " + station_name);

    }
    private void createPassengerWithB(){
        //add passenger to service station as rule B
        passenger_num++;
        Passenger passenger = new Passenger();
        passenger.setId(passenger_num);
        passenger.setArriveTime(time);
        passenger.setServiceTime(RandAvg(S));

        int length = ss1.getPassengerNum();
        ServiceStation selected_station = ss1;
        String station_name = "Service Station 1";

        if(length > ss2.getPassengerNum()){
            length = ss2.getPassengerNum();
            selected_station = ss2;
            station_name = "Service Station 2";
        }
        if(length > ss3.getPassengerNum()){
            length = ss3.getPassengerNum();
            selected_station = ss3;
            station_name = "Service Station 3";
        }
        if(length > ss4.getPassengerNum()){
            length = ss4.getPassengerNum();
            selected_station = ss4;
            station_name = "Service Station 4";
        }
        if(length > ss5.getPassengerNum()){
            selected_station = ss5;
            station_name = "Service Station 5";
        }

        if(selected_station.isEmpty()) {
            passenger.setStartTime(time);
        }
        selected_station.addPassenger(passenger);
        System.out.println("New passenger " + passenger_num + " is arrived at " + passenger.getArriveTime() + " min");
        System.out.println("Service time: " + passenger.getServiceTime() + " min");
        System.out.println("Passenger " + passenger_num + " is dispatched to " + station_name);

    }
    private void createPassengerWithC(){
        //add passenger to service station as rule C
        passenger_num++;
        Passenger passenger = new Passenger();
        passenger.setId(passenger_num);
        passenger.setArriveTime(time);
        passenger.setServiceTime(RandAvg(S));

        //find shortest queue in all service station
        int pos = RandPos(5);
        String station_name;
        ServiceStation selected_station = null;
        if(pos == 1){
            selected_station = ss1;
            station_name = "Service Station 1";
        } else if(pos == 2){
            selected_station = ss2;
            station_name = "Service Station 2";
        } else if(pos == 3){
            selected_station = ss3;
            station_name = "Service Station 3";
        } else if(pos == 4){
            selected_station = ss4;
            station_name = "Service Station 4";
        } else if(pos == 5){
            selected_station = ss5;
            station_name = "Service Station 5";
        } else{
            station_name = "Service Station 1";
            selected_station = ss1;
        }
        if(selected_station.isEmpty()) {
            passenger.setStartTime(time);
        }
        selected_station.addPassenger(passenger);
        System.out.println("New passenger " + passenger_num + " is arrived at " + passenger.getArriveTime() + " min");
        System.out.println("Service time: " + passenger.getServiceTime() + " min");
        System.out.println("Passenger " + passenger_num + " is dispatched to " + station_name);

    }
    private void updateServiceStation(ServiceStation ss){
        //update max queue length
        if(ss.equals(ss1)){
            if(max_queue_length_1 < ss.getPassengerNum()){
                max_queue_length_1 = ss.getPassengerNum();
            }
        } else if(ss.equals(ss2)){
            if(max_queue_length_2 < ss.getPassengerNum()){
                max_queue_length_2 = ss.getPassengerNum();
            }
        } else if(ss.equals(ss3)){
            if(max_queue_length_3 < ss.getPassengerNum()){
                max_queue_length_3 = ss.getPassengerNum();
            }
        } else if(ss.equals(ss4)){
            if(max_queue_length_4 < ss.getPassengerNum()){
                max_queue_length_4 = ss.getPassengerNum();
            }
        } else if(ss.equals(ss5)){
            if(max_queue_length_5 < ss.getPassengerNum()){
                max_queue_length_5 = ss.getPassengerNum();
            }
        }

        Passenger current_passenger = ss.getCurrentPassenger();
        if(current_passenger != null && current_passenger.getStartTime() + current_passenger.getServiceTime() <= time){
            ss.removePassenger();
            //set start time for next passenger
            if(ss.getCurrentPassenger() != null){
                ss.getCurrentPassenger().setStartTime(time);
            }
            System.out.println("Passenger " + current_passenger.getId() + " received service and left station at " + time + " min");
            ss.addServiceTime(current_passenger.getServiceTime());
            //get wait time of each passenger and update max and sum

            int wait_time = time - current_passenger.getArriveTime();

            if(ss.equals(ss1)){
                if(wait_time > max_waiting_time_1){
                    max_waiting_time_1 = wait_time;
                }
                waiting_sum_1 += wait_time;
            } else if(ss.equals(ss2)){
                if(wait_time > max_waiting_time_2){
                    max_waiting_time_2 = wait_time;
                }
                waiting_sum_2 += wait_time;
            } else if(ss.equals(ss3)){
                if(wait_time > max_waiting_time_3){
                    max_waiting_time_3 = wait_time;
                }
                waiting_sum_3 += wait_time;
            } else if(ss.equals(ss4)){
                if(wait_time > max_waiting_time_4){
                    max_waiting_time_4 = wait_time;
                }
                waiting_sum_4 += wait_time;
            } else if(ss.equals(ss5)){
                if(wait_time > max_waiting_time_5){
                    max_waiting_time_5 = wait_time;
                }
                waiting_sum_5 += wait_time;
            }

        }
    }

    //start simulation
    public void start(int type){
        while (ongoingsimulation()){
            if(newpassenger()){
                switch (type){
                    case 2:
                        createPassengerWithB();
                        break;
                    case 3:
                        createPassengerWithC();
                        break;
                    default:
                        createPassengerWithA();
                        break;
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
        System.out.println();
        System.out.println((type == 1 ? "               2A: Round Robin" :
                (type == 2 ? "           2B: Random queue method ": "               2C: Random queue method")));
        System.out.println("--------------------------------------------------------");
        System.out.println("Duration of simulation: " + time);
        System.out.println("Maximum length of queue of station 1: " + max_queue_length_1);
        System.out.println("Maximum length of queue of station 2: " + max_queue_length_2);
        System.out.println("Maximum length of queue of station 3: " + max_queue_length_3);
        System.out.println("Maximum length of queue of station 4: " + max_queue_length_4);
        System.out.println("Maximum length of queue of station 5: " + max_queue_length_5);

        System.out.println("Maximum waiting time for station 1 queue: " + max_waiting_time_1 + " min");
        System.out.println("Maximum waiting time for station 2 queue: " + max_waiting_time_2 + " min");
        System.out.println("Maximum waiting time for station 3 queue: " + max_waiting_time_3 + " min");
        System.out.println("Maximum waiting time for station 4 queue: " + max_waiting_time_4 + " min");
        System.out.println("Maximum waiting time for station 5 queue: " + max_waiting_time_5 + " min");

        System.out.println("Average waiting time for station 1: " + (waiting_sum_1 / ss1.getServiceNum()) + " min");
        System.out.println("Average waiting time for station 2: " + (waiting_sum_2 / ss2.getServiceNum()) + " min");
        System.out.println("Average waiting time for station 3: " + (waiting_sum_3 / ss3.getServiceNum()) + " min");
        System.out.println("Average waiting time for station 4: " + (waiting_sum_4 / ss4.getServiceNum()) + " min");
        System.out.println("Average waiting time for station 5: " + (waiting_sum_5 / ss5.getServiceNum()) + " min");

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
