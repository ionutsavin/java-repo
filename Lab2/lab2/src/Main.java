import java.time.LocalTime;

/**
 * Main class to demonstrate the problem-solving process.
 */
public class Main {
    /**
     * Constructs a new instance of Main.
     */
    public Main() {
        // Default constructor
    }

    /**
     * Main method to run the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Problem problem = getProblem();

        System.out.println("Problem Depots:");
        for (Depot depot : problem.getDepots()) {
            System.out.println(depot);
        }
        System.out.println("Problem Clients:");
        for (Client client : problem.getClients()) {
            System.out.println(client);
        }
        System.out.println("Problem Vehicles:");
        Vehicle[] allVehicles = problem.getVehicles();
        for (Vehicle vehicle : allVehicles) {
            System.out.println(vehicle);
        }

        Tour[] tours = problem.solve();

        System.out.println("Tours:");
        for (Tour tour : tours) {
            System.out.print("Vehicle: " + tour.getVehicle().getName() + ", Assigned Clients: ");
            Client[] clients = tour.getClients();
            for (int i = 0; i < clients.length; i++) {
                System.out.print(clients[i]);
                if (i < clients.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Generates a sample problem instance.
     *
     * @return a problem instance
     */
    private static Problem getProblem() {
        Depot depot1 = new Depot("Depot 1");
        Depot depot2 = new Depot();
        depot2.setName("Depot 2");
        Depot depot3 = new Depot();
        depot3.setName("Depot 1");

        Truck truck1 = new Truck("Truck 1", 100);
        Truck truck2 = new Truck(100);
        truck2.setName("Truck 1");
        Drone drone1 = new Drone("Drone 1", 60);
        Drone drone2 = new Drone(80);
        drone2.setName("Drone 2");

        depot1.setVehicles(truck1, truck2);
        depot2.setVehicles(drone1, drone2);
        depot3.setVehicles(truck2);

        Client client1 = new Client("Client 1", ClientType.REGULAR);
        client1.setMinTime(LocalTime.of(10, 0));
        client1.setMaxTime(LocalTime.of(16, 0));
        Client client2 = new Client(ClientType.PREMIUM);
        client2.setName("Client 2");
        client2.setMinTime(LocalTime.of(7, 30));
        client2.setMaxTime(LocalTime.of(11, 45));
        Client client3 = new Client("Client 1", LocalTime.NOON, LocalTime.MIDNIGHT, ClientType.REGULAR);
        client3.setMinTime(LocalTime.of(10, 0));
        client3.setMaxTime(LocalTime.of(16, 0));
        Client client4 = new Client(LocalTime.of(9, 0), LocalTime.of(17, 0));
        client4.setType(ClientType.PREMIUM);
        client4.setName("Client 3");

        Client[] clients = {client1, client2, client3, client4};

        Problem problem = new Problem();
        problem.setDepots(depot1, depot2, depot3);
        problem.setClients(clients);
        return problem;
    }
}