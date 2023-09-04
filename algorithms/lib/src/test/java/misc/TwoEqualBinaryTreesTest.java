package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoEqualBinaryTreesTest {
    @Test
    void equal() {
        TwoEqualBinaryTrees.TreeNode one = createTree();
        TwoEqualBinaryTrees.TreeNode two = createTree();

        assertTrue(new TwoEqualBinaryTrees().isEqualRecursive(one, two));
        assertTrue(new TwoEqualBinaryTrees().isEqualNonRecursive(one, two));
    }

    @Test
    void unequalSize() {
        TwoEqualBinaryTrees.TreeNode one = createTree();
        TwoEqualBinaryTrees.TreeNode two = createTree();
        two.left.right = null;

        assertFalse(new TwoEqualBinaryTrees().isEqualRecursive(one, two));
        assertFalse(new TwoEqualBinaryTrees().isEqualNonRecursive(one, two));
    }

    @Test
    void unequalValue() {
        TwoEqualBinaryTrees.TreeNode one = createTree();
        TwoEqualBinaryTrees.TreeNode two = createTree();
        two.right.left.value = 666;

        assertFalse(new TwoEqualBinaryTrees().isEqualRecursive(one, two));
        assertFalse(new TwoEqualBinaryTrees().isEqualNonRecursive(one, two));
    }

    private TwoEqualBinaryTrees.TreeNode createTree() {
        TwoEqualBinaryTrees.TreeNode tree = new TwoEqualBinaryTrees.TreeNode();
        tree.value = 1;
        tree.left = new TwoEqualBinaryTrees.TreeNode();
        tree.left.value = 2;
        tree.right = new TwoEqualBinaryTrees.TreeNode();
        tree.right.value = 3;

        tree.left.left = new TwoEqualBinaryTrees.TreeNode();
        tree.left.left.value = 4;
        tree.left.right = new TwoEqualBinaryTrees.TreeNode();
        tree.left.right.value = 5;

        tree.right.left = new TwoEqualBinaryTrees.TreeNode();
        tree.right.left.value = 6;
        tree.right.right = new TwoEqualBinaryTrees.TreeNode();
        tree.right.right.value = 7;

        return tree;
    }
}