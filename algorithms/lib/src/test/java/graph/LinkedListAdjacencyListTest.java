package graph;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListAdjacencyListTest {
    @Test
    void addEdge() {
        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        adjacencyList.addEdge(new Edge(new BinaryNode<>(10), new BinaryNode<>(20)));
        assertEquals(2, adjacencyList.countVertices());
        assertEquals(1, adjacencyList.countEdges());
    }

    @Test
    void uniqueEdges() {
        LinkedListAdjacencyList adjacencyList = new LinkedListAdjacencyList();
        BinaryNode<Integer> first = new BinaryNode<>(10);
        BinaryNode<Integer> second = new BinaryNode<>(20);
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
        BinaryNode<Integer> first = new BinaryNode<>(10);
        adjacencyList.addEdge(new Edge(first, new BinaryNode<>(20)));
        adjacencyList.addEdge(new Edge(first, new BinaryNode<>(30)));
        adjacencyList.addEdge(new Edge(first, new BinaryNode<>(40)));
        adjacencyList.addEdge(new Edge(first, new BinaryNode<>(50)));
        adjacencyList.addEdge(new Edge(first, new BinaryNode<>(60)));

        Optional<List<Vertex<?>>> vertices = adjacencyList.forVertex(first);
        assertTrue(vertices.isPresent());
        List<Vertex<?>> adjacencyListForFirstVertex = vertices.get();
        assertEquals(5, adjacencyListForFirstVertex.size());

        adjacencyListForFirstVertex.forEach(vertex -> {
            Optional<List<Vertex<?>>> vertexList = adjacencyList.forVertex(vertex);
            assertTrue(vertexList.isPresent());
            assertEquals(1, vertexList.get().size());
        });
    }

    @Test
    void removeEdge() {
        // six vertices ...
        BinaryNode<Integer> first = new BinaryNode<>(10);
        BinaryNode<Integer> second = new BinaryNode<>(20);
        BinaryNode<Integer> third = new BinaryNode<>(30);
        BinaryNode<Integer> fourth = new BinaryNode<>(40);
        BinaryNode<Integer> fifth = new BinaryNode<>(50);
        BinaryNode<Integer> sixth = new BinaryNode<>(60);
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

        Optional<List<Vertex<?>>> vertices = adjacencyList.forVertex(first);
        assertTrue(vertices.isPresent());
        List<Vertex<?>> adjacencyListForVertex = vertices.get();

        final int numberAdjacentToFirstVertex = numberVertices - 1;
        assertEquals(numberAdjacentToFirstVertex, adjacencyListForVertex.size());

        assertEquals(numberVertices, adjacencyList.countVertices());
        assertEquals(numberEdges, adjacencyList.countEdges());

        adjacencyList.removeEdge(four);

        vertices = adjacencyList.forVertex(first);
        assertTrue(vertices.isPresent());
        adjacencyListForVertex = vertices.get();
        // first vertex has 4 adjacent vertices after removing one
        assertEquals(numberEdges - 1, adjacencyListForVertex.size());

        vertices = adjacencyList.forVertex(fifth);
        assertTrue(vertices.isPresent());
        adjacencyListForVertex = vertices.get();
        assertEquals(0, adjacencyListForVertex.size());

        assertEquals(6, adjacencyList.countVertices());
        assertEquals(4, adjacencyList.countEdges());
    }
}