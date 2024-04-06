package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import adt.MultiwayHeapPriorityQueue;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;

public abstract class GraphUtils {
    public static final String WEIGHT = "weight";

    public static Tuple<Edge[], Double[]> dijkstra(final Graph graph, final Vertex<?> startVertex) {
        int size = graph.countVertices();
        Double[] weights = new Double[size];
        Edge[] spanningTree = new Edge[size];
        Arrays.fill(weights, Double.MAX_VALUE);

        final MultiwayHeapPriorityQueue priorityQueue = new MultiwayHeapPriorityQueue(size, weights);

        for (int v = 0; v < size; v++) {
            priorityQueue.insert(v);
        }

        final int start = startVertex.number();

        weights[start] = 0.0d;
        priorityQueue.lower(start);

        while (!priorityQueue.empty()) {
            int v = priorityQueue.getMin();
            if (v != start && spanningTree[v] == null) {
                break;
            }

            final Iterator<Edge> adjacencyList = graph.adjacencyList(graph.vertex(v).get());
            while (adjacencyList.hasNext()) {
                Edge edge = adjacencyList.next();
                int w = edge.toVertex().number();
                final Double weight = (Double) edge.getAttributeValue(WEIGHT).get();
                double priority = weights[v] + weight;
                if (priority < weights[w]) {
                    weights[w] = priority;
                    priorityQueue.lower(w);
                    spanningTree[w] = edge;
                }
            }
        }

        return Tuple.of(spanningTree, weights);
    }

    public static class Tuple<P1, P2> {
        P1 one;
        P2 two;

        public Tuple(final P1 one, final P2 two) {
            this.one = one;
            this.two = two;
        }

        public static <P1, P2> Tuple<P1, P2> of(P1 one, P2 two) {
            return new Tuple<>(one, two);
        }

        @Override
        public String toString() {
            return format("(%s, %s)", one, two);
        }
    }

    public static List<Vertex<?>> depthFirstTraversal(final Graph graph, final Vertex<?> start) {
        final List<Vertex<?>> output = new LinkedList<>();
        dft(graph, start, output);
        return output;
    }

    private static void dft(final Graph graph, final Vertex<?> vertex, final List<Vertex<?>> output) {
        output.add(vertex);

        final Iterator<Edge> adjacencyList = graph.adjacencyList(vertex);
        while (adjacencyList.hasNext()) {
            final Edge next = adjacencyList.next();
            final Vertex<?> nextVertex = next.toVertex();
            if (!output.contains(nextVertex)) {
                dft(graph, nextVertex, output);
            }
        }
    }

    public static List<Vertex<?>> breadthFirstTraversal(final Graph graph, final Vertex<?> start) {
        final List<Vertex<?>> output = new LinkedList<>();
        final Queue<Vertex<?>> toVisit = new LinkedList<>();
        final BitSet visited = new BitSet();
        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Vertex<?> current = toVisit.remove();

            visited.set(current.number());
            output.add(current);

            final Iterator<Edge> adjacencyList = graph.adjacencyList(current);
            while (adjacencyList.hasNext()) {
                final Edge next = adjacencyList.next();
                final Vertex<?> nextVertex = next.fromVertex();
                if (!visited.get(nextVertex.number())) {
                    toVisit.add(nextVertex);
                }
            }
        }

        return output;
    }

    public static List<BinaryVertex<Integer>> breadthFirstTraversal(List<BinaryVertex<Integer>> input) {
        List<BinaryVertex<Integer>> nodes = new ArrayList<>();
        for (BinaryVertex<Integer> node : input) {
            BinaryVertex<Integer> left = node.getLeft();
            if (left != null) {
                nodes.add(left);
            }
            BinaryVertex<Integer> right = node.getRight();
            if (right != null) {
                nodes.add(right);
            }
        }

        if (!nodes.isEmpty()) {
            input.addAll(breadthFirstTraversal(nodes));
        }

        return input;
    }

    public static boolean isValidBST(BinaryVertex<Integer> root) {
        return isValidBST(root, MIN_VALUE, MAX_VALUE);
    }

    static boolean isValidBST(BinaryVertex<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        Integer rootValue = root.value();

        if (rootValue < min || rootValue > max) {
            return false;
        }

        BinaryVertex<Integer> left = root.getLeft();

        // if current node is already at the lowest value, there must not be any child to the left (i.e. with smaller value)
        if (root.value() == MIN_VALUE && left != null) {
            return false;
        }

        BinaryVertex<Integer> right = root.getRight();

        // similarly, if current node is at the max value, there must not be any child to the right (i.e. with a larger value)
        if (root.value() == MAX_VALUE && right != null) {
            return false;
        }

        return isValidBST(left, min, rootValue - 1) &&
                isValidBST(right, rootValue + 1, max);
    }
}
