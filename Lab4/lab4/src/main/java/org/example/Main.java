package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Driver("Gigi", 50, "Station"));
        persons.add(new Passenger("Lionel", 25, "Station"));
        persons.add(new Driver("Costel", 47, "Supermarket"));
        persons.add(new Passenger("Alex", 17, "School"));
        List<Driver> drivers = persons.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
        Set<Passenger> passengers = persons.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("The drivers are: " + drivers);
        System.out.println("The passengers are: " + passengers);
    }
}