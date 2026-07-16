package tree;

/**
 * 129. Sum Root to Leaf Numbers
 * Difficulty: Medium
 * Tags: Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * Problem Description:
 * Each root-to-leaf path represents a number. Return the total sum of all root-to-leaf numbers.
 */
public class Leetcode129 {

    // Time: O(n) | Space: O(h) — DFS accumulating the number from root to leaf
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int current) {
        if (node == null) return 0;
        current = current * 10 + node.val;
        if (node.left == null && node.right == null)
            return current;
        return dfs(node.left, current) + dfs(node.right, current);
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
        Leetcode129 sol = new Leetcode129();

        // Test 1: [1,2,3] → 25 (12 + 13)
        TreeNode r1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sol.sumNumbers(r1)); // 25

        // Test 2: [4,9,0,5,1] → 1026 (495 + 491 + 40)
        TreeNode r2 = new TreeNode(4,
                new TreeNode(9, new TreeNode(5), new TreeNode(1)),
                new TreeNode(0));
        System.out.println(sol.sumNumbers(r2)); // 1026

        // Test 3: [] → 0
        System.out.println(sol.sumNumbers(null)); // 0
    }
}
