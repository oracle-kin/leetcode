package tree;

/**
 * 99. Recover Binary Search Tree
 * Difficulty: Medium
 * Tags: Tree, DFS, BFS, Binary Tree, BST
 * URL: https://leetcode.com/problems/recover-binary-search-tree/
 *
 * Problem Description:
 * Recover a BST where exactly two nodes were swapped without changing its structure.
 */
public class Leetcode99 {

    // Time: O(n) | Space: O(h) — inorder traversal finds the two swapped nodes, then swap values back
    private TreeNode first  = null;
    private TreeNode second = null;
    private TreeNode prev   = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        // swap values of the two misplaced nodes
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null && prev.val > node.val) {
            if (first == null) first = prev;
            second = node;
        }
        prev = node;
        inorder(node.right);
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
        // Test: [1,3,null,null,2] → swap 3 & 2 → [3,1,null,null,2]
        TreeNode t1 = new TreeNode(1, new TreeNode(3), null);
        t1.left.right = new TreeNode(2);
        Leetcode99 sol = new Leetcode99();
        sol.recoverTree(t1);
        System.out.println(t1.val + " " + t1.left.val + " " + t1.left.right.val); // 3 1 2
    }
}
