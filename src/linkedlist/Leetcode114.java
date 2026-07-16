package linkedlist;

/**
 * 114. Flatten Binary Tree to Linked List
 * Difficulty: Medium
 * Tags: Linked List, Stack, Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * Problem Description:
 * Flatten a binary tree to a linked list in-place (pre-order, using right pointers).
 *
 * Complexity: O(N) time, O(H) space for recursion stack (H = height of tree)
 */
public class Leetcode114 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void main(String[] args) {
        Leetcode114 sol = new Leetcode114();

        // Test 1: [1,2,5,3,4,null,6] => [1,null,2,null,3,null,4,null,5,null,6]
        TreeNode root = new TreeNode(1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(5, null, new TreeNode(6))
        );
        sol.flatten(root);
        System.out.print("Test 1: ");
        printFlattened(root); // Expected: 1 2 3 4 5 6

        // Test 2: [] => null
        Leetcode114 sol2 = new Leetcode114();
        sol2.flatten(null);
        System.out.println("\nTest 2: null (no error)");
    }

    static void printFlattened(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.val + " ");
            if (curr.left != null) System.out.print("(has left!) ");
            curr = curr.right;
        }
    }
}
