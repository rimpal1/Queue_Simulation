import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter duration of simulation in minutes: ");
        String duration_str = scanner.nextLine().trim();
        int duration = Integer.parseInt(duration_str);
        if(duration <= 0){
            System.err.println("Invalid duration");
            return;
        }

        System.out.print("Please enter average arrival rate in minutes: ");
        String arrive_str = scanner.nextLine().trim();
        int arrive_rate = Integer.parseInt(arrive_str);
        if(arrive_rate <= 0){
            System.err.println("Invalid arrive rate");
            return;
        }

        System.out.print("Please enter average service rate in minutes: ");
        String service_str = scanner.nextLine().trim();
        int service_rate = Integer.parseInt(service_str);
        if(service_rate <= 0){
            System.err.println("Invalid service rate");
            return;
        }

//         simulate option 1
        System.out.println();
        System.out.println("-----Option 1: All passengers are placed in one queue.-----");
        Simulation1 simulation1 = new Simulation1(duration, arrive_rate, service_rate);
        simulation1.start();

        // simulate option 2

        System.out.println();
        System.out.println("-----Option 2: Each service station has their dedicated queue.-----");
        Simulation2 simulation2 = new Simulation2(duration, arrive_rate, service_rate);
        System.out.println();
        System.out.println("--------------------------2A: Round Robin--------------------------");
        simulation2.start(1);
        System.out.println();
        System.out.println("----------2B:Passenger placed in shortest queue--------------------");
        simulation2.initialize();
        simulation2.start(2);
        System.out.println();
        System.out.println("-------------2C: Passenger placed in random queue------------------");
        simulation2.initialize();
        simulation2.start(3);
    }
}
