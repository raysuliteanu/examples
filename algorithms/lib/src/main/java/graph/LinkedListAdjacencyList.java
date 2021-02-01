package graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

class LinkedListAdjacencyList implements AdjacencyList {
    private final Map<Vertex<?>, List<Vertex<?>>> adjacencyList = new HashMap<>();
    private final boolean isDirected;
    private int edgeCount;

    public LinkedListAdjacencyList() {
        this(false);
    }

    LinkedListAdjacencyList(final boolean isDirected) {
        this.isDirected = isDirected;
    }

    @Override
    public void addEdge(Edge edge) {
        Vertex<?> v = edge.vertices()[0];
        Vertex<?> w = edge.vertices()[1];

        synchronized (adjacencyList) {
            if (!adjacencyList.containsKey(v)) {
                adjacencyList.put(v, new LinkedList<>());
            }

            List<Vertex<?>> adjacentToV = adjacencyList.get(v);
            if (adjacentToV.contains(w)) {
                // already exists
                return;
            }

            adjacentToV.add(w);

            if (!isDirected) {
                if (!adjacencyList.containsKey(w)) {
                    adjacencyList.put(w, new LinkedList<>());
                }

                List<Vertex<?>> adjacentToW = adjacencyList.get(w);
                adjacentToW.add(v);
            }

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
        synchronized (adjacencyList) {
            return edgeCount;
        }
    }

    @Override
    public Optional<List<Vertex<?>>> forVertex(final Vertex<?> v) {
        return Optional.ofNullable(adjacencyList.get(v));
    }

    @Override
    public void removeEdge(final Edge edge) {
        Vertex<?> v = edge.vertices()[0];
        Vertex<?> w = edge.vertices()[1];

        synchronized (adjacencyList) {
            List<Vertex<?>> vertices = adjacencyList.get(v);
            if (vertices != null) {
                vertices.removeIf(vertex -> vertex.number() == w.number());
            }

            if (!isDirected) {
                vertices = adjacencyList.get(w);
                if (vertices != null) {
                    vertices.removeIf(vertex -> vertex.number() == v.number());
                }
            }

            --edgeCount;
        }
    }

    @Override
    public Set<Vertex<?>> vertices() {
        synchronized (adjacencyList) {
            final TreeSet<Vertex<?>> vertices = new TreeSet<>(Comparator.comparingInt(Vertex::number));
            vertices.addAll(adjacencyList.keySet());
            return Collections.unmodifiableSet(vertices);
        }
    }
}
