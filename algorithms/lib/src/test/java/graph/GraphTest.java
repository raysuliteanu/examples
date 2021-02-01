package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static graph.GraphUtils.bfs;
import static graph.GraphUtils.isValidBST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    @Test
    void validBfs() {
        String result = generateBfs(binaryTree());
        assertEquals("1 2 3 4 5 6 7", result);
    }

    @Test
    void validGeneralBfs() {
        final Vertex<?> root = new SimpleVertex(1, "1");
        final Vertex<?> two = new SimpleVertex(2, "2");
        final Vertex<?> three = new SimpleVertex(3, "3");

        final Graph graph = GraphBuilder.defaultGraph()
                .withEdge(Edge.of(root, two))
                .withEdge(Edge.of(root, three))
                .withEdge(Edge.of(two, new SimpleVertex(4, "4")))
                .withEdge(Edge.of(two, new SimpleVertex(5, "5")))
                .withEdge(Edge.of(three, new SimpleVertex(6, "6")))
                .withEdge(Edge.of(three, new SimpleVertex(7, "7")))
                .build();
        List<Vertex<?>> output = GraphUtils.bfs(graph, root);
        for (int i = 1; i <= output.size(); i++) {
            assertEquals(i, output.get(i - 1).number());
        }
    }

    private BinaryNode<Integer> binaryTree() {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        BinaryNode<Integer> left = new BinaryNode<>(2);
        BinaryNode<Integer> right = new BinaryNode<>(3);
        root.setLeft(left);
        root.setRight(right);

        BinaryNode<Integer> leftLeft = new BinaryNode<>(4);
        BinaryNode<Integer> leftRight = new BinaryNode<>(5);
        left.setLeft(leftLeft);
        left.setRight(leftRight);

        BinaryNode<Integer> rightLeft = new BinaryNode<>(6);
        BinaryNode<Integer> rightRight = new BinaryNode<>(7);
        right.setLeft(rightLeft);
        right.setRight(rightRight);
        return root;
    }

    private String generateBfs(final BinaryNode<Integer> node) {
        if (node == null) {
            return "";
        }

        List<BinaryNode<Integer>> nodes = new ArrayList<>();
        nodes.add(node);

        List<String> values = bfs(nodes).stream()
                                        .map(aNode -> String.valueOf(aNode.value()))
                                        .collect(Collectors.toList());

        return String.join(" ", values);
    }

    @Test
    void invalidBst1() {
        BinaryNode<Integer> root = new BinaryNode<>(Integer.MIN_VALUE);
        BinaryNode<Integer> left = new BinaryNode<>(Integer.MIN_VALUE);
        root.setLeft(left);

        assertFalse(isValidBST(root));
    }

    @Test
    void invalidBst2() {
        BinaryNode<Integer> root = new BinaryNode<>(Integer.MAX_VALUE);
        BinaryNode<Integer> right = new BinaryNode<>(Integer.MIN_VALUE);
        root.setRight(right);

        assertFalse(isValidBST(root));
    }

    @Test
    void validBst() {
        BinaryNode<Integer> root = new BinaryNode<>(2);
        BinaryNode<Integer> left = new BinaryNode<>(1);
        BinaryNode<Integer> right = new BinaryNode<>(3);
        root.setLeft(left);
        root.setRight(right);

        assertTrue(isValidBST(root));
    }
}
