import java.util.*;

/**
 * Represents a problem instance involving depots, clients, and vehicles.
 */
public class Problem {
    private Depot[] depots;
    private Client[] clients;

    /**
     * Constructs a new instance of Problem.
     */
    public Problem() {
        // Default constructor
    }

    /**
     * Sets the depots for the problem.
     *
     * @param depots the depots to be set
     */
    public void setDepots(Depot... depots) {
        Set<Depot> uniqueDepots = new HashSet<>(Arrays.asList(depots));
        this.depots = uniqueDepots.toArray(new Depot[0]);
    }

    /**
     * Sets the clients for the problem.
     *
     * @param clients the clients to be set
     */
    public void setClients(Client... clients) {
        Set<Client> uniqueClients = new HashSet<>(Arrays.asList(clients));
        this.clients = uniqueClients.toArray(new Client[0]);
    }

    /**
     * Gets the depots in the problem.
     *
     * @return an array of depots in the problem
     */
    public Depot[] getDepots() {
        return depots;
    }

    /**
     * Gets the clients in the problem.
     *
     * @return an array of clients in the problem
     */
    public Client[] getClients() {
        return clients;
    }

    /**
     * Gets all vehicles from all depots in the problem.
     *
     * @return an array of all vehicles in the problem
     */
    public Vehicle[] getVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots) {
            allVehicles.addAll(Arrays.asList(depot.getVehicles()));
        }
        return allVehicles.toArray(new Vehicle[0]);
    }

    /**
     * Solves the problem by allocating clients to vehicles.
     *
     * @return an array of tours representing the solution to the problem
     */
    public Tour[] solve() {
        List<Tour> tours = new ArrayList<>();
        for (Depot depot : depots) {
            for (Vehicle vehicle : depot.getVehicles()) {
                for (Client client : clients) {
                    if (!vehicle.isAssigned() && !client.isAssigned()) {
                        vehicle.setAssigned();
                        client.setAssigned();
                        Tour tour = new Tour(vehicle, new Client[]{client});
                        tours.add(tour);
                        break;
                    }
                }
            }
        }
        return tours.toArray(new Tour[0]);
    }
}