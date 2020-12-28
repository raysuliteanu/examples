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

        String result = generateBfs(root);
        assertEquals("1 2 3 4 5 6 7", result);
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
