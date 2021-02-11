package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultGraphTest {
    @Test
    void addEdges() {
        Vertex<String> first = new SimpleVertex(10);
        Vertex<String> second = new SimpleVertex(20);
        Vertex<String> third = new SimpleVertex(30);

        DefaultGraph graph = new DefaultGraph();
        graph.insert(new Edge(first, second));
        graph.insert(new Edge(third, second));
        assertTrue(graph.edge(first, second));
        assertTrue(graph.edge(second, first));
        assertFalse(graph.edge(first, third));
        assertTrue(graph.edge(second, third));
        assertTrue(graph.edge(third, second));
        assertTrue(graph.edge(second, third));
        assertFalse(graph.edge(third, third));
    }
}