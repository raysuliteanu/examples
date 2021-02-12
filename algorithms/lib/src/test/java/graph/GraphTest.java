package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static graph.GraphUtils.breadthFirstTraversal;
import static graph.GraphUtils.depthFirstTraversal;
import static graph.GraphUtils.isValidBST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    @Test
    void dft() {
        final Vertex<?> root = new SimpleVertex(1);
        final Graph graph = simpleGraph(root);
        List<Vertex<?>> output = depthFirstTraversal(graph, root);
        assertEquals(7, output.size());
    }

    @Test
    void validBft() {
        String result = generateBreadthFirstTraversal(binaryTree());
        assertEquals("1 2 3 4 5 6 7", result);
    }

    @Test
    void validGeneralBft() {
        final Vertex<?> root = new SimpleVertex(1);
        final Graph graph = simpleGraph(root);
        List<Vertex<?>> output = breadthFirstTraversal(graph, root);
        for (int i = 1; i <= output.size(); i++) {
            assertEquals(i, output.get(i - 1).number());
        }
    }

    @Test
    void invalidBst1() {
        BinaryVertex<Integer> root = new BinaryVertex<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
        BinaryVertex<Integer> left = new BinaryVertex<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
        root.setLeft(left);

        assertFalse(isValidBST(root));
    }

    @Test
    void invalidBst2() {
        BinaryVertex<Integer> root = new BinaryVertex<>(Integer.MAX_VALUE, Integer.MAX_VALUE);
        BinaryVertex<Integer> right = new BinaryVertex<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
        root.setRight(right);

        assertFalse(isValidBST(root));
    }

    @Test
    void validBst() {
        BinaryVertex<Integer> root = new BinaryVertex<>(2, 2);
        BinaryVertex<Integer> left = new BinaryVertex<>(1, 1);
        BinaryVertex<Integer> right = new BinaryVertex<>(3, 3);
        root.setLeft(left);
        root.setRight(right);

        assertTrue(isValidBST(root));
    }

    private Graph simpleGraph(final Vertex<?> root) {
        final Vertex<?> two = new SimpleVertex(2);
        final Vertex<?> three = new SimpleVertex(3);

        return GraphBuilder.defaultGraph()
                .withEdge(Edge.of(root, two))
                .withEdge(Edge.of(root, three))
                .withEdge(Edge.of(two, new SimpleVertex(4)))
                .withEdge(Edge.of(two, new SimpleVertex(5)))
                .withEdge(Edge.of(three, new SimpleVertex(6)))
                .withEdge(Edge.of(three, new SimpleVertex(7)))
                .build();
    }

    private BinaryVertex<Integer> binaryTree() {
        BinaryVertex<Integer> root = new BinaryVertex<>(1, 1);
        BinaryVertex<Integer> left = new BinaryVertex<>(2, 2);
        BinaryVertex<Integer> right = new BinaryVertex<>(3, 3);
        root.setLeft(left);
        root.setRight(right);

        BinaryVertex<Integer> leftLeft = new BinaryVertex<>(4, 4);
        BinaryVertex<Integer> leftRight = new BinaryVertex<>(5, 5);
        left.setLeft(leftLeft);
        left.setRight(leftRight);

        BinaryVertex<Integer> rightLeft = new BinaryVertex<>(6, 6);
        BinaryVertex<Integer> rightRight = new BinaryVertex<>(7, 7);
        right.setLeft(rightLeft);
        right.setRight(rightRight);
        return root;
    }

    private String generateBreadthFirstTraversal(final BinaryVertex<Integer> node) {
        if (node == null) {
            return "";
        }

        List<BinaryVertex<Integer>> nodes = new ArrayList<>();
        nodes.add(node);

        List<String> values = breadthFirstTraversal(nodes).stream()
                .map(aNode -> String.valueOf(aNode.number()))
                .collect(Collectors.toList());

        return String.join(" ", values);
    }
}
