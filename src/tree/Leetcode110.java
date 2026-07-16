package tree;

/**
 * 110. Balanced Binary Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/balanced-binary-tree/
 *
 * Problem Description:
 * Determine if a binary tree is height-balanced.
 */
public class Leetcode110 {

    // Time: O(n) | Space: O(h) — DFS returning height, -1 signals unbalanced
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        if (left == -1) return -1;
        int right = height(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    // ── TreeNode ─────────────────────────────────
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // ── Test Cases ───────────────────────────────
    public static void main(String[] args) {
        Leetcode110 sol = new Leetcode110();

        // Test 1: [3,9,20,null,null,15,7] → true
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.isBalanced(r1)); // true

        // Test 2: [1,2,2,3,3,null,null,4,4] → false
        TreeNode r2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3, new TreeNode(4), new TreeNode(4)), new TreeNode(3)),
                new TreeNode(2));
        System.out.println(sol.isBalanced(r2)); // false

        // Test 3: [] → true
        System.out.println(sol.isBalanced(null)); // true
    }
}
