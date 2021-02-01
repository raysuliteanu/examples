package graph;

import java.util.Iterator;
import java.util.Optional;

public interface Graph {
    default boolean isDirected() { return false;}

    int countVertices();

    int countEdges();

    void insert(Edge edge);

    void remove(Edge edge);

    boolean edge(Vertex<?> v, Vertex<?> w);

    Optional<Vertex<?>> vertex(int number);

    Iterator<Vertex<?>> adjacencyList(Vertex<?> v);
}
