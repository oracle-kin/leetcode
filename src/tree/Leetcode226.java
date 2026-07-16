package tree;

/**
 * 226. Invert Binary Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/invert-binary-tree/
 *
 * Problem Description:
 * Invert a binary tree (mirror reflection).
 */
public class Leetcode226 {

    // Time: O(n) | Space: O(h) — recursively swap left & right children
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left  = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left  = right;
        root.right = left;
        return root;
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
        Leetcode226 sol = new Leetcode226();

        // Test 1: [4,2,7,1,3,6,9] → [4,7,2,9,6,3,1]
        TreeNode r1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode inv = sol.invertTree(r1);
        System.out.println(inv.val);          // 4
        System.out.println(inv.left.val);     // 7
        System.out.println(inv.right.val);    // 2

        // Test 2: [] → null
        System.out.println(sol.invertTree(null)); // null

        // Test 3: [2,1,3] → [2,3,1]
        TreeNode r3 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode inv2 = sol.invertTree(r3);
        System.out.println(inv2.left.val);  // 3
        System.out.println(inv2.right.val); // 1
    }
}
