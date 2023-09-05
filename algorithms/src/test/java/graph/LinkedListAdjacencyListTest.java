package graph;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListAdjacencyListTest {
    @Test
    void addEdge() {
        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        adjacencyList.addEdge(new Edge(new BinaryVertex<>(10), new BinaryVertex<>(20)));
        assertEquals(2, adjacencyList.countVertices());
        assertEquals(1, adjacencyList.countEdges());
    }

    @Test
    void uniqueEdges() {
        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        BinaryVertex<Integer> first = new BinaryVertex<>(10);
        BinaryVertex<Integer> second = new BinaryVertex<>(20);
        adjacencyList.addEdge(new Edge(first, second));
        assertEquals(2, adjacencyList.countVertices());
        assertEquals(1, adjacencyList.countEdges());

        adjacencyList.addEdge(new Edge(first, second));
        assertEquals(2, adjacencyList.countVertices());
        assertEquals(1, adjacencyList.countEdges());
    }

    @Test
    void findAdjacencyList() {
        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        BinaryVertex<Integer> first = new BinaryVertex<>(10);
        adjacencyList.addEdge(new Edge(first, new BinaryVertex<>(20)));
        adjacencyList.addEdge(new Edge(first, new BinaryVertex<>(30)));
        adjacencyList.addEdge(new Edge(first, new BinaryVertex<>(40)));
        adjacencyList.addEdge(new Edge(first, new BinaryVertex<>(50)));
        adjacencyList.addEdge(new Edge(first, new BinaryVertex<>(60)));

        Optional<List<Edge>> vertices = adjacencyList.forVertex(first);
        assertTrue(vertices.isPresent());
        List<Edge> adjacencyListForFirstVertex = vertices.get();
        assertEquals(5, adjacencyListForFirstVertex.size());

        adjacencyListForFirstVertex.forEach(edge -> {
            Optional<List<Edge>> edges = adjacencyList.forVertex(edge.vertices()[0]);
            assertTrue(edges.isPresent());
            assertEquals(5, edges.get().size());
        });
    }

    @Test
    void removeEdge() {
        // six vertices ...
        BinaryVertex<Integer> first = new BinaryVertex<>(10);
        BinaryVertex<Integer> second = new BinaryVertex<>(20);
        BinaryVertex<Integer> third = new BinaryVertex<>(30);
        BinaryVertex<Integer> fourth = new BinaryVertex<>(40);
        BinaryVertex<Integer> fifth = new BinaryVertex<>(50);
        BinaryVertex<Integer> sixth = new BinaryVertex<>(60);
        final int numberVertices = 6;

        // and five edges ...
        Edge one = new Edge(first, second);
        Edge two = new Edge(first, third);
        Edge three = new Edge(first, fourth);
        Edge four = new Edge(first, fifth);
        Edge five = new Edge(first, sixth);
        final int numberEdges = 5;

        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        adjacencyList.addEdge(one);
        adjacencyList.addEdge(two);
        adjacencyList.addEdge(three);
        adjacencyList.addEdge(four);
        adjacencyList.addEdge(five);

        Optional<List<Edge>> edges = adjacencyList.forVertex(first);
        assertTrue(edges.isPresent());

        List<Edge> adjacencyListForVertex = edges.get();

        final int numberAdjacentToFirstVertex = numberVertices - 1;
        assertEquals(numberAdjacentToFirstVertex, adjacencyListForVertex.size());
        assertEquals(numberVertices, adjacencyList.countVertices());
        assertEquals(numberEdges, adjacencyList.countEdges());

        adjacencyList.removeEdge(four);

        edges = adjacencyList.forVertex(fifth);
        assertFalse(edges.isPresent());

        edges = adjacencyList.forVertex(first);
        assertTrue(edges.isPresent());
        adjacencyListForVertex = edges.get();
        // first vertex has 4 adjacent edges after removing one
        assertEquals(numberEdges - 1, adjacencyListForVertex.size());
        assertEquals(5, adjacencyList.countVertices());
        assertEquals(4, adjacencyList.countEdges());
    }
}