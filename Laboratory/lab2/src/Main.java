import java.time.LocalTime;
public class Main {
    public static void main(String[] args) {
        Client c1 = new Client();
        c1.setName("Client 1");
        c1.setMinTime(LocalTime.of(8, 0));
        c1.setMaxTime(LocalTime.of(12, 30));
        System.out.println(c1.getName());
        Client c2 = new Client("Client 2");
        System.out.println(c2);
        Client c3 = new Client("Client 3", LocalTime.NOON, LocalTime.MIDNIGHT);
        System.out.println(c3);

        Depot d = new Depot("Depot");
        System.out.println(d);
        Vehicle v1 = new Vehicle("Vehicle 1");
        Vehicle v2 = new Vehicle("Vehicle 2");
        v1.setName("Car 1");
        System.out.println(v1.getName());
        d.addVehicle(v1);
        System.out.println(v1.getDepot());
        System.out.println(v2);
        d.getVehicles();
        System.out.println(d);
    }
}