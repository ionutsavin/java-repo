package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        Faker fakePassenger = new Faker();
        Faker fakeDriver = new Faker();

        persons.add(new Driver("Gigi", 50, "Station"));
        persons.add(new Passenger("Leon", 26, "Station"));
        persons.add(new Passenger("Lionel", 25, "Station"));
        persons.add(new Driver("Nigel", 50, "Subway"));
        persons.add(new Passenger("Oriol", 25, "Subway"));
        persons.add(new Driver(fakeDriver.name().firstName(), 47, fakeDriver.address().streetName()));
        persons.add(new Passenger(fakePassenger.name().firstName(), 17, fakePassenger.address().streetName()));

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

        Problem problem = new Problem(persons);
        List<String> destinationsOfDrivers = problem.getDestinationsOfDrivers();
        Map<String, Set<Person>> destinationMap = problem.getDestinationMap();
        Map<Driver, Passenger> result = problem.solveProblem();

        System.out.println("Destinations of drivers: " + destinationsOfDrivers);
        System.out.println("Destination map: " + destinationMap);
        System.out.println("Carpooling result: " + result);

        Graph<String, DefaultEdge> randomGraph = RandomGraphGenerator.generateRandomGraph(10, 10, 0.1);

        System.out.println("Edges of the graph:");
        Set<DefaultEdge> edges = RandomGraphGenerator.getEdges(randomGraph);
        for (DefaultEdge edge : edges) {
            System.out.println(edge);
        }

        Map<String, String> assignment = RandomGraphGenerator.solve(randomGraph);
        System.out.println("Greedy algorithm:");
        for (Map.Entry<String, String> entry : assignment.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        MaximumSet independentSet = new MaximumSet(randomGraph);

        System.out.println("Edges from the matching:");
        Set<DefaultEdge> matchingEdges = independentSet.calculateMatching();
        for (DefaultEdge edge : matchingEdges) {
            System.out.println(edge);
        }

        Set<String> nodesNotInMatching = independentSet.findMaximumCardinalitySet();
        System.out.println("Maximum set of persons that don't have the same destination:");
        for (String node : nodesNotInMatching) {
            System.out.println(node);
        }
    }
}