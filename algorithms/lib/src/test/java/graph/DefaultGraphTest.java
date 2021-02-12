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
        graph.insert(Edge.of(first, second));
        graph.insert(Edge.of(third, second));
        assertTrue(graph.edge(first, second));
        assertFalse(graph.edge(first, third));
        assertTrue(graph.edge(third, second));
        assertFalse(graph.edge(third, third));
    }
}