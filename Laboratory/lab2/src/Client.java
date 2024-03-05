import java.time.LocalTime;
public class Client {
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;
    public Client(){}

    public Client(String name)
    {
        this.name = name;
    }
    public Client(String name, LocalTime minTime, LocalTime maxTime)
    {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }
    @java.lang.Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }
}
