package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;

import java.util.*;

public class MaximumSet {

    private final Graph<String, DefaultEdge> graph;

    public MaximumSet(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }

    public Set<DefaultEdge> calculateMatching() {
        Set<String> drivers = new HashSet<>();
        Set<String> passengers = new HashSet<>();
        for (String vertex : graph.vertexSet()) {
            if (vertex.startsWith("Driver")) {
                drivers.add(vertex);
            } else if (vertex.startsWith("Passenger")) {
                passengers.add(vertex);
            }
        }

        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> algorithm =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, drivers, passengers);
        MatchingAlgorithm.Matching<String, DefaultEdge> matching = algorithm.getMatching();
        return matching.getEdges();
    }

    public Set<String> getNodesFromMatching() {
        Set<DefaultEdge> matchingEdges = calculateMatching();
        Set<String> nodesFromMatching = new HashSet<>();
        for (DefaultEdge edge : matchingEdges) {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            nodesFromMatching.add(source);
            nodesFromMatching.add(target);
        }
        return nodesFromMatching;
    }

    public Set<String> getNodesNotInMatching() {
        Set<DefaultEdge> matchingEdges = calculateMatching();
        Set<String> nodesNotInMatching = new HashSet<>(graph.vertexSet());
        for (DefaultEdge edge : matchingEdges) {
            String source = graph.getEdgeSource(edge);
            String target = graph.getEdgeTarget(edge);
            nodesNotInMatching.remove(source);
            nodesNotInMatching.remove(target);
        }
        return nodesNotInMatching;
    }

    public Set<String> findMaximumCardinalitySet() {
        Set<String> nodesNotInMatching = getNodesNotInMatching();
        Set<String> nodesFromMatching = getNodesFromMatching();
        Set<String> maximumCardinalitySet = new HashSet<>(nodesNotInMatching);
        for (String node : nodesFromMatching) {
            boolean isIncidentToMaximumCardinalitySet = false;
            for (String maxCardinalityNode : maximumCardinalitySet) {
                if (graph.containsEdge(node, maxCardinalityNode) || graph.containsEdge(maxCardinalityNode, node)) {
                    isIncidentToMaximumCardinalitySet = true;
                    break;
                }
            }
            if (!isIncidentToMaximumCardinalitySet) {
                maximumCardinalitySet.add(node);
            }
        }
        return maximumCardinalitySet;
    }
}
