import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a client with a name, type, and time constraints.
 */
public class Client {
    private String name;
    private ClientType type;
    private LocalTime minTime;
    private LocalTime maxTime;
    private boolean isAssigned;

    /**
     * Constructs a new client with default values.
     */
    public Client() {
    }

    /**
     * Constructs a new client with the specified name.
     *
     * @param name the name of the client
     */
    public Client(String name) {
        this.name = name;
    }

    /**
     * Constructs a new client with the specified type.
     *
     * @param type the type of the client
     */
    public Client(ClientType type) {
        this.type = type;
    }

    /**
     * Constructs a new client with the specified name and type.
     *
     * @param name the name of the client
     * @param type the type of the client
     */
    public Client(String name, ClientType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Constructs a new client with the specified minimum and maximum times.
     *
     * @param minTime the minimum time constraint
     * @param maxTime the maximum time constraint
     */
    public Client(LocalTime minTime, LocalTime maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    /**
     * Constructs a new client with the specified name, minimum and maximum times.
     *
     * @param name    the name of the client
     * @param minTime the minimum time constraint
     * @param maxTime the maximum time constraint
     */
    public Client(String name, LocalTime minTime, LocalTime maxTime) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    /**
     * Constructs a new client with the specified name, minimum and maximum times, and type.
     *
     * @param name    the name of the client
     * @param minTime the minimum time constraint
     * @param maxTime the maximum time constraint
     * @param type    the type of the client
     */
    public Client(String name, LocalTime minTime, LocalTime maxTime, ClientType type) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.type = type;
    }

    /**
     * Sets the maximum time constraint for the client.
     *
     * @param maxTime the maximum time constraint
     */
    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * Sets the minimum time constraint for the client.
     *
     * @param minTime the minimum time constraint
     */
    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the client.
     *
     * @param type the type of the client
     */
    public void setType(ClientType type) {
        this.type = type;
    }

    /**
     * Marks the client as assigned.
     */
    public void setAssigned() {
        isAssigned = true;
    }

    /**
     * Gets the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the minimum time constraint for the client.
     *
     * @return the minimum time constraint
     */
    public LocalTime getMinTime() {
        return minTime;
    }

    /**
     * Gets the maximum time constraint for the client.
     *
     * @return the maximum time constraint
     */
    public LocalTime getMaxTime() {
        return maxTime;
    }

    /**
     * Gets the type of the client.
     *
     * @return the type of the client
     */
    public ClientType getType() {
        return type;
    }

    /**
     * Checks if the client is assigned.
     *
     * @return true if the client is assigned, false otherwise
     */
    public boolean isAssigned() {
        return isAssigned;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return a string representation of the client
     */
    @Override
    public String toString() {
        return "Client{" +
                "name=" + this.getName() +
                ", minTime=" + this.getMinTime() +
                ", maxTime=" + this.getMaxTime() +
                ", type=" + this.getType() +
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
        Client client = (Client) o;
        return Objects.equals(name, client.name) && type == client.type && Objects.equals(minTime, client.minTime) && Objects.equals(maxTime, client.maxTime);
    }

    /**
     * Returns a hash code value for the client.
     *
     * @return a hash code value for the client
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, type, minTime, maxTime);
    }
}
