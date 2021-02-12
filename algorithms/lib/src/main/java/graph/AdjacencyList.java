package graph;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdjacencyList {
    void addEdge(Edge edge);

    int countEdges();

    int countVertices();

    Optional<List<Edge>> forVertex(Vertex<?> v);

    void removeEdge(Edge edge);

    Set<Vertex<?>> vertices();
}
