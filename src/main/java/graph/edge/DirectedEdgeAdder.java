package graph.edge;

import java.util.*;

import graph.*;

public class DirectedEdgeAdder implements EdgeAdder {

    @Override
    public Map<Vertex, Set<Vertex>> addEdge(
        Map<Vertex, Set<Vertex>> vertices,
        Vertex vertex1,
        Vertex vertex2
    ) {
        vertices.computeIfPresent(vertex1, (vertex, vertices1) -> {
            vertices1.add(vertex2);
            return vertices1;
        });
        return vertices;
    }

}
