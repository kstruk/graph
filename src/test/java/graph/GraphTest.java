package graph;

import java.util.*;
import java.util.stream.*;

import org.junit.*;
import static org.junit.Assert.*;

public class GraphTest {

    private Graph directed;

    private Graph undirected;

    @Before
    public void setUp() {
        directed = fillGraph(GraphFactory.directed());
        undirected = fillGraph(GraphFactory.undirected());
    }

    @Test(expected = IllegalStateException.class)
    public void graph_duplicate_fail() {
        Vertex a = directed.addVertex("A", new UserData());
        fail("Exception must be thrown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void graph_not_belong_fail() {
        Vertex a = directed.getVertex("A");
        Vertex f = new Vertex("F", new UserData());

        directed.addEdge(a, f);
        fail("Exception must be thrown");
    }

    @Test
    public void directed_graph_success() {
        Vertex a = directed.getVertex("A");
        Vertex e = directed.getVertex("E");

        List<Vertex> path = directed.getPath(a, e);

        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void directed_graph_success_1() {
        Vertex a = directed.getVertex("A");
        Vertex b = directed.getVertex("B");

        List<Vertex> path = directed.getPath(b, a);
        System.out.println(path);

        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void directed_graph_fail() {
        Vertex a = directed.getVertex("A");
        Vertex e = directed.getVertex("E");

        List<Vertex> path = directed.getPath(e, a);
        System.out.println(path);

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

    @Test
    public void undirected_graph_forward_success() {
        Vertex a = undirected.getVertex("A");
        Vertex e = undirected.getVertex("E");

        List<Vertex> path = undirected.getPath(a, e);

        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void undirected_graph_backward_success() {
        Vertex a = undirected.getVertex("A");
        Vertex e = undirected.getVertex("E");

        List<Vertex> path = undirected.getPath(e, a);

        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void undirected_graph_fail() {
        Vertex a = undirected.getVertex("A");
        Vertex f = undirected.addVertex("F", new UserData());

        List<Vertex> path = undirected.getPath(a, f);

        assertNotNull(path);
        assertTrue(path.isEmpty());
    }

    @Test
    public void visit_graph() {
        System.out.println("Directed graph");
        directed.apply(this::printVertex);
        System.out.println();

        System.out.println("Undirected graph");
        undirected.apply(this::printVertex);
    }

    private void printVertex(Vertex vertex, Set<Vertex> vertices) {
        String collect = vertices.stream()
            .map(Vertex::getKey)
            .collect(Collectors.joining(", "));
        System.out.println(String.format(
            "vertex: %s; connected: %s;",
            vertex.getKey(), collect));
    }

    private Graph fillGraph(Graph graph) {
        Vertex a = graph.addVertex("A", new UserData());
        Vertex b = graph.addVertex("B", new UserData());
        Vertex c = graph.addVertex("C", new UserData());
        Vertex d = graph.addVertex("D", new UserData());
        Vertex e = graph.addVertex("E", new UserData());

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, d);
        graph.addEdge(c, d);
        graph.addEdge(d, e);

        graph.addEdge(d, c);
        graph.addEdge(c, a);

        return graph;
    }

}
