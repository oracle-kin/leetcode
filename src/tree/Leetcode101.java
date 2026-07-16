package tree;

/**
 * 101. Symmetric Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/symmetric-tree/
 *
 * Problem Description:
 * Check whether a binary tree is a mirror of itself (symmetric around its center).
 */
public class Leetcode101 {

    // Time: O(n) | Space: O(h) — recursively compare left & right subtrees as mirrors
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
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
        Leetcode101 sol = new Leetcode101();

        // Test 1: [1,2,2,3,4,4,3] → true
        TreeNode r1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(sol.isSymmetric(r1)); // true

        // Test 2: [1,2,2,null,3,null,3] → false
        TreeNode r2 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));
        System.out.println(sol.isSymmetric(r2)); // false

        // Test 3: [] → true
        System.out.println(sol.isSymmetric(null)); // true
    }
}
