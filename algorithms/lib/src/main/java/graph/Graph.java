package graph;

import java.util.Iterator;

public interface Graph {
    default boolean isDirected() { return false;}

    int countVertices();

    int countEdges();

    void insert(Edge edge);

    void remove(Edge edge);

    boolean edge(Vertex<?> v, Vertex<?> w);

    Iterator<Vertex<?>> adjacencyList(Vertex<?> v);
}
