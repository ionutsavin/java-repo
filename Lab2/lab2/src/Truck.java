/**
 * Represents a truck, a type of vehicle with a specified capacity.
 */
public class Truck extends Vehicle {
    private final int capacity;

    /**
     * Constructs a new truck with the specified name and capacity.
     *
     * @param name     the name of the truck
     * @param capacity the capacity of the truck
     */
    public Truck(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }

    /**
     * Constructs a new truck with the specified capacity and no name.
     *
     * @param capacity the capacity of the truck
     */
    public Truck(int capacity) {
        super("");
        this.capacity = capacity;
    }

    /**
     * Gets the capacity of the truck.
     *
     * @return the capacity of the truck
     */
    public int getCapacity() {
        return capacity;
    }
}
