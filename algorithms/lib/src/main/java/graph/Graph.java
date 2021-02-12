package graph;

import java.util.Iterator;
import java.util.Optional;

public interface Graph {
    default boolean isDirected() { return false;}

    int countEdges();

    void insert(Edge edge);

    void remove(Edge edge);

    boolean edge(Vertex<?> v, Vertex<?> w);

    Optional<Vertex<?>> vertex(int number);

    Iterator<Edge> adjacencyList(Vertex<?> v);
}
