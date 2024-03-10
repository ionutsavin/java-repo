import java.util.*;

/**
 * Represents a depot where vehicles are stationed.
 */
public class Depot {
    private String name;
    private Vehicle[] vehicles;

    /**
     * Constructs a new depot with no name.
     */
    public Depot() {
    }

    /**
     * Constructs a new depot with the specified name.
     *
     * @param name the name of the depot
     */
    public Depot(String name) {
        this.name = name;
    }

    /**
     * Sets the vehicles stationed at the depot.
     * Duplicates are removed to ensure uniqueness.
     *
     * @param vehicles the vehicles stationed at the depot
     */
    public void setVehicles(Vehicle... vehicles) {
        Set<Vehicle> uniqueVehicles = new HashSet<>(Arrays.asList(vehicles));
        this.vehicles = uniqueVehicles.toArray(new Vehicle[0]);
        for (Vehicle v : this.vehicles) {
            v.setDepot(this);
        }
    }

    /**
     * Sets the name of the depot.
     *
     * @param name the name of the depot
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the depot.
     *
     * @return the name of the depot
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the vehicles stationed at the depot.
     *
     * @return an array of vehicles stationed at the depot
     */
    public Vehicle[] getVehicles() {
        return vehicles;
    }

    /**
     * Returns a string representation of the depot.
     *
     * @return a string representation of the depot
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Depot{name=").append(name).append(", vehicles=[");
        if (vehicles != null) {
            for (int i = 0; i < vehicles.length; i++) {
                sb.append(vehicles[i].getName());
                if (i < vehicles.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(name, depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }

    /**
     * Returns a hash code value for the depot.
     *
     * @return a hash code value for the depot
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }
}
