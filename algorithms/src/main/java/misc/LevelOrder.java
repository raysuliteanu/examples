package misc;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        final List<TreeNode> level = new ArrayList<>();
        level.add(root);

        while (!level.isEmpty()) {
            List<TreeNode> currentLevel = new ArrayList<>(level);

            level.clear();

            List<Integer> vals = new ArrayList<>();

            for (TreeNode n : currentLevel) {
                if (n.left != null) {
                    level.add(n.left);
                }
                if (n.right != null) {
                    level.add(n.right);
                }

                vals.add(n.value);
            }

            result.add(vals);
        }

        return result;
    }

    public List<List<Integer>> levelOrderStreams(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        final List<TreeNode> level = new ArrayList<>();
        level.add(root);

        while (!level.isEmpty()) {
            List<TreeNode> currentLevel = new ArrayList<>(level);

            level.clear();

            result.add(currentLevel.stream()
                    .map(n -> {
                        if (n.left != null) {
                            level.add(n.left);
                        }
                        if (n.right != null) {
                            level.add(n.right);
                        }
                        return n.value;
                    })
                    .collect(Collectors.toList()));
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = buildBinaryTree(2048);
        final LevelOrder levelOrder = new LevelOrder();
        final List<List<Integer>> order = levelOrder.levelOrder(root);
        for (int i = 0; i < order.size(); i++) {
            final List<Integer> level = order.get(i);
            System.out.printf("level %d (size %d): %s\n", i + 1, level.size(), level);
        }
        final List<List<Integer>> orderStreams = levelOrder.levelOrderStreams(root);
        for (int i = 0; i < order.size(); i++) {
            final List<Integer> level = orderStreams.get(i);
            System.out.printf("level %d (size %d): %s\n", i + 1, level.size(), level);
        }

        long start = System.nanoTime();
        levelOrder.levelOrder(root);
        long endOrder = System.nanoTime() - start;

        start = System.nanoTime();
        levelOrder.levelOrderStreams(root);
        long endOrderStreams = System.nanoTime() - start;
        final Duration orderDuration = Duration.ofNanos(endOrder);
        final Duration orderDurationStreams = Duration.ofNanos(endOrderStreams);

        System.out.printf("no streams: %s\n   streams: %s", orderDuration, orderDurationStreams);
    }

    private static TreeNode buildBinaryTree(final int size) {
        TreeNode root = new TreeNode(1);
        Queue<TreeNode> todo = new ArrayDeque<>();
        todo.add(root);
        int cnt = 2;
        while (!todo.isEmpty() && cnt < size) {
            TreeNode node = todo.remove();
            TreeNode left = new TreeNode(cnt++);
            TreeNode right = new TreeNode(cnt++);
            todo.add(left);
            todo.add(right);
            node.left = left;
            node.right = right;
        }

        return root;
    }
}
