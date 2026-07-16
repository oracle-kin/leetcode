package tree;

/**
 * 100. Same Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/same-tree/
 *
 * Problem Description:
 * Check if two binary trees are structurally identical and nodes have the same values.
 */
public class Leetcode100 {

    // Time: O(n) | Space: O(h) — recursively compare both trees simultaneously
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
        Leetcode100 sol = new Leetcode100();

        // Test 1: [1,2,3] vs [1,2,3] → true
        TreeNode a = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode b = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sol.isSameTree(a, b)); // true

        // Test 2: [1,2] vs [1,null,2] → false
        TreeNode c = new TreeNode(1, new TreeNode(2), null);
        TreeNode d = new TreeNode(1, null, new TreeNode(2));
        System.out.println(sol.isSameTree(c, d)); // false

        // Test 3: [1,2,1] vs [1,1,2] → false
        TreeNode e = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode f = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        System.out.println(sol.isSameTree(e, f)); // false
    }
}
