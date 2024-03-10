import java.util.Objects;

/**
 * Represents an abstract vehicle.
 */
public abstract class Vehicle {
    /**
     * Represents a vehicle with a name.
     */
    protected String name;
    private Depot depot;
    private boolean isAssigned;

    /**
     * Constructs a new vehicle with the specified name.
     *
     * @param name the name of the vehicle
     */
    public Vehicle(String name) {
        this.name = name;
        depot = new Depot();
    }

    /**
     * Sets the name of the vehicle.
     *
     * @param name the name of the vehicle
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the depot where the vehicle is stationed.
     *
     * @param depot the depot where the vehicle is stationed
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * Marks the vehicle as assigned.
     */
    public void setAssigned() {
        isAssigned = true;
    }

    /**
     * Gets the name of the vehicle.
     *
     * @return the name of the vehicle
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the depot where the vehicle is stationed.
     *
     * @return the name of the depot where the vehicle is stationed
     */
    public String getDepot() {
        return depot.getName();
    }

    /**
     * Checks if the vehicle is assigned.
     *
     * @return true if the vehicle is assigned, false otherwise
     */
    public boolean isAssigned() {
        return isAssigned;
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "name=" + this.getName() +
                ", depot=" + this.getDepot() +
                '}';
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
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot);
    }

    /**
     * Returns a hash code value for the vehicle.
     *
     * @return a hash code value for the vehicle
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, depot);
    }
}
