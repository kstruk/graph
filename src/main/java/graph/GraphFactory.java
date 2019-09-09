package graph;

import java.util.*;

import graph.edge.*;
import graph.path.*;

public class GraphFactory {

    public static Graph directed() {
        return new BaseGraph(new HashMap<>(), new DirectedEdgeAdder(), new BasePathFinder());
    }

    public static Graph undirected() {
        return new BaseGraph(new HashMap<>(), new UndirectedEdgeAdder(), new BasePathFinder());
    }

}
