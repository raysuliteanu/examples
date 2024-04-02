package misc;

/*
Constraints:

    The number of nodes in the tree is in the range [0, 5000].
    -10^4 <= Node.val <= 10^4
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(depth(root.left) - depth(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    int depth(TreeNode node) {
        if (node == null) {return -1;}

        return 1 + Math.max(depth(node.left), depth(node.right));
    }
}
