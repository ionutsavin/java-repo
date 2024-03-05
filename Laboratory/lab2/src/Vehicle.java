public class Vehicle {
    private String name;
    private Depot depot;

    public Vehicle(java.lang.String name) {
        this.name = name;
        depot = new Depot();
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public java.lang.String getName() {
        return name;
    }

    public String getDepot() {
        return depot.getName();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Vehicle{" +
                "name=" + this.getName() +
                ", depot=" + this.getDepot() +
                '}';
    }
}
