package graph.path;

import java.util.*;

import graph.*;

public interface PathFinder {

    List<Vertex> findPath(
        Map<Vertex, Set<Vertex>> vertices,
        Vertex vertex1,
        Vertex vertex2
    );

}
