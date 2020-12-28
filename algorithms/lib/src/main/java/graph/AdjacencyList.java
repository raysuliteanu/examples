package graph;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdjacencyList {
    void addEdge(Edge edge);

    int countVertices();

    int countEdges();

    Optional<List<Vertex<?>>> forVertex(Vertex<?> v);

    void removeEdge(Edge edge);

    Set<Integer> vertices();
}
