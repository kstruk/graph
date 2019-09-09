package graph.edge;

import java.util.*;

import graph.*;

public class UndirectedEdgeAdder implements EdgeAdder {

    @Override
    public Map<Vertex, Set<Vertex>> addEdge(
        Map<Vertex, Set<Vertex>> graph,
        Vertex vertex1,
        Vertex vertex2
    ) {
        graph.computeIfPresent(vertex1, (vertex, vertices) -> {
            vertices.add(vertex2);
            return vertices;
        });
        graph.computeIfPresent(vertex2, (vertex, vertices) -> {
            vertices.add(vertex1);
            return vertices;
        });
        return graph;
    }

}
