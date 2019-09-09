package graph.path;

import java.util.*;

import graph.*;

public class BasePathFinder implements PathFinder {

    @Override
    public List<Vertex> findPath(
        Map<Vertex, Set<Vertex>> vertices,
        Vertex vertex1,
        Vertex vertex2
    ) {
        Map<Vertex, Vertex> backPath = new HashMap<>();
        backPath.put(vertex1, vertex1);

        Queue<Vertex> frontier = new ArrayDeque<>();
        frontier.add(vertex1);

        while (!frontier.isEmpty()) {
            Vertex current = frontier.poll();
            if (current.equals(vertex2)) {
                break;
            }
            for (Vertex v : vertices.get(current)) {
                if (!backPath.containsValue(v)) {
                    frontier.add(v);
                    backPath.putIfAbsent(v, current);
                }
            }
        }

        return backPath.containsKey(vertex2)
            ? composePath(backPath, vertex1, vertex2)
            : Collections.emptyList();
    }

    private List<Vertex> composePath(
        Map<Vertex, Vertex> backPath,
        Vertex vertex1,
        Vertex vertex2
    ) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex current = vertex2;
        while (!current.equals(vertex1)) {
            path.addFirst(current);
            current = backPath.get(current);
        }
        path.addFirst(vertex1);
        return path;
    }

}
