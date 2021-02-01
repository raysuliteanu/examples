package graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public abstract class GraphUtils {
    public static List<Vertex<?>> bfs(final Graph graph, final Vertex<?> start) {
        final List<Vertex<?>> output = new LinkedList<>();
        final Queue<Vertex<?>> toVisit = new LinkedList<>();
        final BitSet visited = new BitSet();
        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Vertex<?> current = toVisit.remove();

            visited.set(current.number());
            output.add(current);

            final Iterator<Vertex<?>> adjacencyList = graph.adjacencyList(current);
            while (adjacencyList.hasNext()) {
                final Vertex<?> next = adjacencyList.next();
                if (!visited.get(next.number())) {
                    toVisit.add(next);
                }
            }
        }

        return output;
    }

    public static List<BinaryNode<Integer>> bfs(List<BinaryNode<Integer>> input) {
        List<BinaryNode<Integer>> nodes = new ArrayList<>();
        for (BinaryNode<Integer> node : input) {
            BinaryNode<Integer> left = node.getLeft();
            if (left != null) {
                nodes.add(left);
            }
            BinaryNode<Integer> right = node.getRight();
            if (right != null) {
                nodes.add(right);
            }
        }

        if (!nodes.isEmpty()) {
            input.addAll(bfs(nodes));
        }

        return input;
    }

    public static boolean isValidBST(BinaryNode<Integer> root) {
        return isValidBST(root, MIN_VALUE, MAX_VALUE);
    }

    static boolean isValidBST(BinaryNode<Integer> root, Integer min, Integer max) {
        if (root == null) { return true; }

        Integer rootValue = root.value();

        if (rootValue < min || rootValue > max) { return false; }

        BinaryNode<Integer> left = root.getLeft();

        // if current node is already at the lowest value, there must not be any child to the left (i.e. with smaller value)
        if (root.value() == MIN_VALUE && left != null) { return false; }

        BinaryNode<Integer> right = root.getRight();

        // similarly, if current node is at the max value, there must not be any child to the right (i.e. with a larger value)
        if (root.value() == MAX_VALUE && right != null) { return false; }

        return isValidBST(left, min, rootValue - 1) &&
                isValidBST(right, rootValue + 1, max);
    }
}
