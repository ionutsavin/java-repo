package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private final List<Person> persons;

    public Problem(List<Person> persons) {
        this.persons = persons;
    }

    public List<String> getDestinationsOfDrivers() {
        return persons.stream()
                .filter(person -> person instanceof Driver)
                .map(Person::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Set<Person>> getDestinationMap() {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::getDestination, Collectors.toSet()));
    }

    public Map<Driver, Passenger> solveProblem() {
        Map<String, Set<Person>> destinationMap = getDestinationMap();
        Map<Driver, Passenger> carpoolingResult = new HashMap<>();

        for (String destination : destinationMap.keySet()) {
            Set<Person> passengers = destinationMap.get(destination).stream()
                    .filter(person -> person instanceof Passenger)
                    .collect(Collectors.toSet());
            Set<Person> drivers = persons.stream()
                    .filter(person -> person instanceof Driver && person.getDestination().equals(destination))
                    .collect(Collectors.toSet());
            for (Person driver : drivers) {
                if (!passengers.isEmpty()) {
                    Passenger compatiblePassenger = (Passenger) passengers.iterator().next();
                    carpoolingResult.put((Driver) driver, compatiblePassenger);
                    passengers.remove(compatiblePassenger);
                }
            }
        }
        return carpoolingResult;
    }
}
