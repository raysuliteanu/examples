package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class LinkedListAdjacencyList implements AdjacencyList {
    private final Map<Integer, List<Vertex<?>>> adjacencyList = new HashMap<>();
    private int edgeCount;

    @Override
    public void addEdge(Edge edge) {
        Vertex<?> v = edge.vertices()[0];
        Vertex<?> w = edge.vertices()[1];

        synchronized (adjacencyList) {
            if (!adjacencyList.containsKey(v.number())) {
                adjacencyList.put(v.number(), new LinkedList<>());
            }

            List<Vertex<?>> adjacentToV = adjacencyList.get(v.number());
            if (adjacentToV.contains(w)) {
                // already exists
                return;
            }

            adjacentToV.add(w);

            if (!adjacencyList.containsKey(w.number())) {
                adjacencyList.put(w.number(), new LinkedList<>());
            }

            List<Vertex<?>> adjacentToW = adjacencyList.get(w.number());
            adjacentToW.add(v);

            ++edgeCount;
        }
    }

    @Override
    public int countVertices() {
        synchronized (adjacencyList) {
            return adjacencyList.size();
        }
    }

    @Override
    public int countEdges() {
        return edgeCount;
    }

    @Override
    public Optional<List<Vertex<?>>> forVertex(final Vertex<?> v) {
        return Optional.ofNullable(adjacencyList.get(v.number()));
    }

    @Override
    public void removeEdge(final Edge edge) {
        Vertex<?> v = edge.vertices()[0];
        Vertex<?> w = edge.vertices()[1];

        synchronized (adjacencyList) {
            List<Vertex<?>> vertices = adjacencyList.get(v.number());
            if (vertices != null) {
                vertices.removeIf(vertex -> vertex.number() == w.number());
            }

            vertices = adjacencyList.get(w.number());
            if (vertices != null) {
                vertices.removeIf(vertex -> vertex.number() == v.number());
            }

            --edgeCount;
        }
    }

    @Override
    public Set<Integer> vertices() {
        return Collections.unmodifiableSet(adjacencyList.keySet());
    }
}
