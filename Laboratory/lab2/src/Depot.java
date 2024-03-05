import java.util.*;

public class Depot {
    private String name;
    private Vector<String> vehicles;

    public Depot() {
    }

    public Depot(String name) {
        this.name = name;
        vehicles = new Vector<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getVehicles() {
        System.out.println(vehicles);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle.getName());
        vehicle.setDepot(this);
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
