package misc;

public class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode() {
    }

    TreeNode(int value) {
        this.value = value;
    }

    TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
