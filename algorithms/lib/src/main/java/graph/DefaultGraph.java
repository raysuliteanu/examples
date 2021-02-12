package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
        Iterator<Edge> iterator = adjacencyList(v);
        while (iterator.hasNext()) {
            if (iterator.next().vertices()[1].number() == w.number()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Optional<Vertex<?>> vertex(final int number) {
        return adjacencyList.vertices().stream().filter(v -> v.number() == number).findFirst();
    }

    @Override
    public Iterator<Edge> adjacencyList(final Vertex<?> v) {
        return new AdjacencyListIterator(adjacencyList.forVertex(v).orElseGet(Collections::emptyList));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph").append(" ").append(name).append("\n");

        Set<Vertex<?>> vertices = adjacencyList.vertices();
        for (Vertex<?> vertex : vertices) {
            builder.append(vertex).append(": ");
            List<Integer> ids = new ArrayList<>();
            Iterator<Edge> iterator = adjacencyList(vertex);
            while (iterator.hasNext()) {
                ids.add(iterator.next().vertices()[0].number());
            }
            builder.append(Arrays.toString(ids.stream().sorted().toArray())).append("\n");
        }

        return builder.toString();
    }

    static class AdjacencyListIterator implements Iterator<Edge> {
        private final Iterator<Edge> iterator;

        public AdjacencyListIterator(final List<Edge> list) {
            iterator = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Edge next() {
            return iterator.next();
        }
    }

}
