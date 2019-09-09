package graph;

import java.util.*;
import java.util.function.*;

import static java.util.Objects.*;

import graph.edge.*;
import graph.path.*;

public class BaseGraph implements Graph {

    private Map<Vertex, Set<Vertex>> vertices;

    private EdgeAdder adder;

    private PathFinder finder;

    public BaseGraph(
        Map<Vertex, Set<Vertex>> vertices,
        EdgeAdder adder,
        PathFinder finder
    ) {
        this.vertices = requireNonNull(vertices);
        this.adder = requireNonNull(adder);
        this.finder = requireNonNull(finder);
    }

    @Override
    public Vertex getVertex(String key) {
        return vertices.keySet().stream()
            .filter(v -> v.getKey().equals(key))
            .findFirst().orElse(null);
    }

    @Override
    public Vertex addVertex(String key, UserData userData) {
        Vertex vertex = new Vertex(key, userData);
        assertAlreadyContains(vertex);
        vertices.put(vertex, new LinkedHashSet<>());
        return vertex;
    }

    @Override
    public void addEdge(Vertex vertex1, Vertex vertex2) {
        assertBelongToGraph(vertex1);
        assertBelongToGraph(vertex2);
        adder.addEdge(vertices, vertex1, vertex2);
    }

    @Override
    public List<Vertex> getPath(Vertex vertex1, Vertex vertex2) {
        assertBelongToGraph(vertex1);
        assertBelongToGraph(vertex2);
        return finder.findPath(Map.copyOf(vertices), vertex1, vertex2);
    }

    @Override
    public void apply(BiConsumer<Vertex, Set<Vertex>> consumer) {
        vertices.forEach(consumer);
    }

    private void assertAlreadyContains(Vertex vertex) {
        if (vertices.containsKey(vertex)) {
            throw new IllegalStateException(String.format(
                "Graph already contains vertex with key: %s",
                vertex.getKey()));
        }
    }

    private void assertBelongToGraph(Vertex vertex) {
        if (!vertices.containsKey(vertex)) {
            throw new IllegalArgumentException(String.format(
                "Vertex must belong to graph: %s",
                vertex));
        }
    }

}
