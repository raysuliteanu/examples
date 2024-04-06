package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static graph.GraphUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void dft() {
        final Vertex<?> root = Vertex.of(1);
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
        final Vertex<?> root = Vertex.of(1);
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

    @Test
    void dijkstraSPT() {
        final Vertex<?> root = Vertex.of(0);
        final Graph graph = simpleGraphWithWeights(root);
        final GraphUtils.Tuple<Edge[], Double[]> tuple = dijkstra(graph, root);
        final Double[] weights = tuple.two;
        final Double[] expected = {0.0, 0.41, 0.8200000000000001, 0.86, 0.5, 0.29};
        assertArrayEquals(expected, weights);
    }

    private Graph simpleGraph(final Vertex<?> root) {
        final Vertex<?> two = Vertex.of(2);
        final Vertex<?> three = Vertex.of(3);

        return GraphBuilder.defaultGraph()
                .withEdge(Edge.of(root, two))
                .withEdge(Edge.of(root, three))
                .withEdge(Edge.of(two, Vertex.of(4)))
                .withEdge(Edge.of(two, Vertex.of(5)))
                .withEdge(Edge.of(three, Vertex.of(6)))
                .withEdge(Edge.of(three, Vertex.of(7)))
                .build();
    }

    private Graph simpleGraphWithWeights(final Vertex<?> root) {
        final Vertex<?> one = Vertex.of(1);
        final Vertex<?> two = Vertex.of(2);
        final Vertex<?> three = Vertex.of(3);
        final Vertex<?> four = Vertex.of(4);
        final Vertex<?> five = Vertex.of(5);

        return GraphBuilder.defaultGraph()
                .withEdge(Edge.of(root, one).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.41d)))
                .withEdge(Edge.of(root, five).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.29d)))
                .withEdge(Edge.of(one, two).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.51d)))
                .withEdge(Edge.of(one, four).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.32d)))
                .withEdge(Edge.of(two, three).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.50d)))
                .withEdge(Edge.of(three, root).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.45d)))
                .withEdge(Edge.of(three, five).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.38d)))
                .withEdge(Edge.of(four, two).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.32d)))
                .withEdge(Edge.of(four, three).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.36d)))
                .withEdge(Edge.of(five, one).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.29d)))
                .withEdge(Edge.of(five, four).with(new DoubleAttribute(GraphUtils.WEIGHT, 0.21d)))
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
