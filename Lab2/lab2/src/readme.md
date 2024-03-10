## Compulsory Component - output
```bash
Client 1
Client{name=Client 2, minTime=null, maxTime=null}
Client{name=Client 3, minTime=12:00, maxTime=00:00}
Depot{name=Depot, vehicles=[]}
Car 1
Depot
Vehicle{name=Vehicle 2, depot=null}
[Car 1]
Depot{name=Depot, vehicles=[Car 1]}
```
## Homework Component - output
The generated equals and hashCode from IntelliJ for the Depot class are not working properly.
```bash
Problem Depots:
Depot{name=Depot 1, vehicles=[Truck 1]}
Depot{name=Depot 2, vehicles=[Drone 2, Drone 1]}
Depot{name=Depot 1, vehicles=[Truck 1]}
Problem Clients:
Client{name=Client 1, minTime=10:00, maxTime=16:00, type=REGULAR}
Client{name=Client 3, minTime=09:00, maxTime=17:00, type=PREMIUM}
Client{name=Client 2, minTime=07:30, maxTime=11:45, type=PREMIUM}
Problem Vehicles:
Vehicle{name=Truck 1, depot=Depot 1}
Vehicle{name=Drone 2, depot=Depot 2}
Vehicle{name=Drone 1, depot=Depot 2}
Vehicle{name=Truck 1, depot=Depot 1}
Tours:
Vehicle: Truck 1, Assigned Clients: Client{name=Client 1, minTime=10:00, maxTime=16:00, type=REGULAR}
Vehicle: Drone 2, Assigned Clients: Client{name=Client 3, minTime=09:00, maxTime=17:00, type=PREMIUM}
Vehicle: Drone 1, Assigned Clients: Client{name=Client 2, minTime=07:30, maxTime=11:45, type=PREMIUM}
```