package graph;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DefaultGraph implements Graph {
    private final AdjacencyList adjacencyList = new LinkedListAdjacencyList();
    private final String name;

    public DefaultGraph() {
        name = UUID.randomUUID().toString();
    }

    public DefaultGraph(final String name) {
        this.name = name;
    }

    @Override
    public int countVertices() {
        return adjacencyList.countVertices();
    }

    @Override
    public int countEdges() {
        return adjacencyList.countEdges();
    }

    @Override
    public void insert(final Edge edge) {
        adjacencyList.addEdge(edge);
    }

    @Override
    public void remove(final Edge edge) {
        adjacencyList.removeEdge(edge);
    }

    @Override
    public boolean edge(final Vertex<?> v, final Vertex<?> w) {
        Iterator<Vertex<?>> iterator = adjacencyList(v);
        while (iterator.hasNext()) {
            if (iterator.next().number() == w.number()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<Vertex<?>> adjacencyList(final Vertex<?> v) {
        return new AdjacencyListIterator(adjacencyList.forVertex(v).orElseGet(Collections::emptyList));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph").append(" ").append(name).append("\n");

        Set<Integer> vertices = adjacencyList.vertices();
        for (Integer vertex : vertices) {
            builder.append(vertex).append(": ");
            Iterator<Vertex<?>> iterator = adjacencyList(new SimpleVertex(vertex, null));
            while (iterator.hasNext()) {
                builder.append(iterator.next().number()).append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    static class AdjacencyListIterator implements Iterator<Vertex<?>> {
        private final Iterator<Vertex<?>> iterator;

        public AdjacencyListIterator(final List<Vertex<?>> list) {
            iterator = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Vertex<?> next() {
            return iterator.next();
        }
    }

}
