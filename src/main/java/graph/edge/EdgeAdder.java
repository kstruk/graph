package graph.edge;

import java.util.*;

import graph.*;

public interface EdgeAdder {

    Map<Vertex, Set<Vertex>> addEdge(
        Map<Vertex, Set<Vertex>> vertices,
        Vertex vertex1,
        Vertex vertex2
    );

}
