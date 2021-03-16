package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopologicalSortTest {

    @Test
    void order() {
        Graph graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(0, 1))
                .withEdge(Edge.of(0, 2))
                .withEdge(Edge.of(1, 3))
                .withEdge(Edge.of(2, 3))
                .build();
        assertArrayEquals(new int[]{0, 2, 1, 3}, TopologicalSort.topologicalSort(4, graph));

        graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(2, 0))
                .withEdge(Edge.of(2, 1))
                .withEdge(Edge.of(0, 2))
                .build();
        assertArrayEquals(new int[]{}, TopologicalSort.topologicalSort(3, graph));

        graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(0, 1))
                .withEdge(Edge.of(1, 0))
                .withEdge(Edge.of(0, 2))
                .build();
        assertArrayEquals(new int[]{}, TopologicalSort.topologicalSort(3, graph));

        graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(1, 0))
                .build();
        assertArrayEquals(new int[]{1, 0}, TopologicalSort.topologicalSort(2, graph));

        graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(0, 2))
                .withEdge(Edge.of(0, 1))
                .withEdge(Edge.of(1, 3))
                .withEdge(Edge.of(2, 3))
                .withEdge(Edge.of(3, 1))
                .build();
        assertArrayEquals(new int[]{}, TopologicalSort.topologicalSort(4, graph));
    }

}