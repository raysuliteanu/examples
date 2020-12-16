package graph;

import java.util.ArrayList;
import java.util.List;

public abstract class GraphUtils {
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

}
