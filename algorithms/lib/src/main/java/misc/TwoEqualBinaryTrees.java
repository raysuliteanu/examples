package misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwoEqualBinaryTrees {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public boolean isEqualRecursive(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        }

        if (one == null || two == null) {
            return false;
        }

        if (one.value != two.value) {
            return false;
        }

        return isEqualRecursive(one.left, two.left) && isEqualRecursive(one.right, two.right);
    }

    public boolean isEqualNonRecursive(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        }

        if (one == null || two == null) {
            return false;
        }

        final Stack<Integer> stackOne = fillStack(one);
        final Stack<Integer> stackTwo = fillStack(two);

        if (stackOne.size() != stackTwo.size()) {
            return false;
        }

        while (!stackOne.isEmpty()) {
            if (!stackOne.pop().equals(stackTwo.pop())) {
                return false;
            }
        }

        return true;
    }

    private Stack<Integer> fillStack(final TreeNode node) {
        Stack<Integer> stack = new Stack<>();

        Queue<TreeNode> todo = new LinkedList<>();
        todo.add(node);
        while (!todo.isEmpty()) {
            TreeNode curr = todo.remove();
            stack.push(curr.value);
            if (curr.left != null) {
                todo.add(curr.left);
            }

            if (curr.right != null) {
                todo.add(curr.right);
            }
        }

        return stack;
    }
}
