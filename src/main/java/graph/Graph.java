package graph;

import java.util.*;
import java.util.function.*;

public interface Graph {

    Vertex getVertex(String key);

    Vertex addVertex(String key, UserData userData);

    void addEdge(Vertex vertex1, Vertex vertex2);

    List<Vertex> getPath(Vertex vertex1, Vertex vertex2);

    void apply(BiConsumer<Vertex, Set<Vertex>> consumer);

}
