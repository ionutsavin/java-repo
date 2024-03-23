package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.*;

public class RandomGraphGenerator {

    public static Graph<String, DefaultEdge> generateRandomGraph(int numDrivers, int numPassengers, double edgeProbability) {
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Random random = new Random();

        for (int i = 0; i < numDrivers; i++) {
            graph.addVertex("Driver" + i);
        }
        for (int i = 0; i < numPassengers; i++) {
            graph.addVertex("Passenger" + i);
        }
        for (String driver : graph.vertexSet()) {
            for (String passenger : graph.vertexSet()) {
                if (driver.startsWith("Driver") && passenger.startsWith("Passenger")) {
                    if (random.nextDouble() < edgeProbability) {
                        graph.addEdge(driver, passenger);
                    }
                }
            }
        }
        return graph;
    }

    public static Set<DefaultEdge> getEdges(Graph<String, DefaultEdge> graph) {
        return graph.edgeSet();
    }

    public static Map<String, String> solve(Graph<String, DefaultEdge> graph) {
        Map<String, String> assignment = new HashMap<>();
        Set<String> assignedPassengers = new HashSet<>();

        for (String driver : graph.vertexSet()) {
            if (driver.startsWith("Driver")) {
                for (DefaultEdge edge : graph.edgesOf(driver)) {
                    String passenger = graph.getEdgeTarget(edge);
                    if (passenger.startsWith("Passenger") && !assignedPassengers.contains(passenger)) {
                        assignment.put(driver, passenger);
                        assignedPassengers.add(passenger);
                        break;
                    }
                }
            }
        }
        return assignment;
    }
}
