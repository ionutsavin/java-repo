/**
 * Represents a drone, a type of vehicle with a specified maximum flight duration.
 */
public class Drone extends Vehicle {
    private final int duration;

    /**
     * Constructs a new drone with the specified name and maximum flight duration.
     *
     * @param name     the name of the drone
     * @param duration the maximum flight duration of the drone
     */
    public Drone(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Constructs a new drone with the specified maximum flight duration and no name.
     *
     * @param duration the maximum flight duration of the drone
     */
    public Drone(int duration) {
        super("");
        this.duration = duration;
    }

    /**
     * Gets the maximum flight duration of the drone.
     *
     * @return the maximum flight duration of the drone
     */
    public int getDuration() {
        return duration;
    }
}
