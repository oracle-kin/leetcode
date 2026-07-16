package tree;

/**
 * 104. Maximum Depth of Binary Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Problem Description:
 * Given the root of a binary tree, return its maximum depth.
 */
public class Leetcode104 {

    // Time: O(n) | Space: O(h) — post-order DFS, depth = 1 + max(left, right)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
        Leetcode104 sol = new Leetcode104();

        // Test 1: [3,9,20,null,null,15,7] → 3
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.maxDepth(r1)); // 3

        // Test 2: [1,null,2] → 2
        System.out.println(sol.maxDepth(new TreeNode(1, null, new TreeNode(2)))); // 2

        // Test 3: [] → 0
        System.out.println(sol.maxDepth(null)); // 0
    }
}
