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
    private final Map<Vertex<?>, List<Edge>> adjacencyList = new HashMap<>();
    private final Map<Integer, Vertex<?>> vertices = new HashMap<>();

    private volatile int edgeCount;

    @Override
    public void addEdge(Edge edge) {
        Vertex<?> v = edge.vertices()[0];
        Vertex<?> w = edge.vertices()[1];

        synchronized (adjacencyList) {
            List<Edge> adjacentToV = adjacencyList.computeIfAbsent(v, k -> new LinkedList<>());

            if (!adjacentToV.contains(edge)) {
                adjacentToV.add(edge);

                ++edgeCount;

                synchronized (vertices) {
                    vertices.putIfAbsent(v.number(), v);
                    vertices.putIfAbsent(w.number(), w);
                }
            }
        }
    }

    @Override
    public int countEdges() {
        synchronized (adjacencyList) {
            return edgeCount;
        }
    }

    @Override
    public int countVertices() {
        return vertices.size();
    }

    @Override
    public Optional<List<Edge>> forVertex(final Vertex<?> v) {
        return Optional.ofNullable(adjacencyList.get(v));
    }

    @Override
    public void removeEdge(final Edge edge) {
        Vertex<?> v = edge.vertices()[0];

        synchronized (adjacencyList) {
            final List<Edge> vEdges = adjacencyList.get(v);
            if (vEdges != null) {
                vEdges.remove(edge);
                --edgeCount;

                synchronized (vertices) {
                    if (vEdges.isEmpty()) {
                        vertices.remove(v.number());
                    }

                    Vertex<?> w = edge.vertices()[1];

                    if (!adjacencyList.containsKey(w) || adjacencyList.get(w).isEmpty()) {
                        vertices.remove(w.number());
                    }
                }
            }
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
