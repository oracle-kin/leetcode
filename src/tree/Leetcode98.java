package tree;

/**
 * 98. Validate Binary Search Tree
 * Difficulty: Medium
 * Tags: Tree, DFS, BFS, Binary Tree, BST
 * URL: https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Problem Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree.
 */
public class Leetcode98 {

    // Time: O(n) | Space: O(h) — validate using valid range for each node
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
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
        Leetcode98 sol = new Leetcode98();

        // Test 1: [2,1,3] → true
        TreeNode r1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(sol.isValidBST(r1)); // true

        // Test 2: [5,1,4,null,null,3,6] → false
        TreeNode r2 = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(sol.isValidBST(r2)); // false

        // Test 3: [2,2,2] → false
        TreeNode r3 = new TreeNode(2, new TreeNode(2), new TreeNode(2));
        System.out.println(sol.isValidBST(r3)); // false
    }
}
