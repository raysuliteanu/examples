package graph;

import java.util.*;

class LinkedListAdjacencyList implements AdjacencyList {
    private final Map<Vertex<?>, List<Edge>> adjacencyList = new HashMap<>();
    private final Map<Integer, Vertex<?>> vertices = new HashMap<>();

    private volatile int edgeCount;

    @Override
    public void addEdge(Edge edge) {
        Vertex<?> v = edge.fromVertex();
        Vertex<?> w = edge.toVertex();

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
        Vertex<?> v = edge.fromVertex();

        synchronized (adjacencyList) {
            final List<Edge> vEdges = adjacencyList.get(v);
            if (vEdges != null) {
                vEdges.remove(edge);
                --edgeCount;

                synchronized (vertices) {
                    if (vEdges.isEmpty()) {
                        vertices.remove(v.number());
                    }

                    Vertex<?> w = edge.toVertex();

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
