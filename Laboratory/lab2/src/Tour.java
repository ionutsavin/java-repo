/**
 * Represents a tour, which is a combination of a vehicle and assigned clients.
 */
public class Tour {
    private final Vehicle vehicle;
    private final Client[] clients;

    /**
     * Constructs a new tour with the specified vehicle and clients.
     *
     * @param vehicle the vehicle assigned to the tour
     * @param clients the clients assigned to the tour
     */
    public Tour(Vehicle vehicle, Client[] clients) {
        this.vehicle = vehicle;
        this.clients = clients;
    }

    /**
     * Gets the vehicle assigned to the tour.
     *
     * @return the vehicle assigned to the tour
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Gets the clients assigned to the tour.
     *
     * @return an array of clients assigned to the tour
     */
    public Client[] getClients() {
        return clients;
    }
}
