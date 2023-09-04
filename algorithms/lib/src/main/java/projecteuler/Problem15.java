package projecteuler;

import java.util.Objects;
import java.util.Stack;

public class Problem15 {
    public static void main(String[] args) {

        Node root = buildGraph();
        Stack<String> paths = new Stack<>();
        dft(paths, "", root);
        System.out.println(paths);
        System.out.println("count = " + paths.size());
    }

    // 'path' is just for debugging
    private static void dft(final Stack<String> paths, final String path, final Node node) {
        if (node.left == null && node.right == null) {
            paths.push(path + " " + node.number);
        }

        if (node.left != null) {
            dft(paths, path + " " + node.number, node.left);
        }

        if (node.right != null) {
            dft(paths, path + " " + node.number, node.right);
        }
    }

    private static Node buildGraph() {
        Node root = new Node(0);

        final Node one = new Node(1);
        final Node three = new Node(3);
        final Node four = new Node(4);
        final Node two = new Node(2);
        final Node five = new Node(5);
        final Node six = new Node(6);
        final Node seven = new Node(7);
        final Node eight = new Node(8);

        root.right = one;
        root.left = three;

        one.right = two;
        one.left = four;

        two.left = five;

        three.right = four;
        three.left = six;

        four.right = five;
        four.left = seven;

        five.left = eight;

        six.right = seven;

        seven.right = eight;

        return root;
    }

    private static class Node {
        final Integer number;
        Node left;
        Node right;

        public Node(final Integer number) {
            this.number = number;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Node node = (Node) o;
            return number.equals(node.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }
}
