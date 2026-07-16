package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * Difficulty: Easy
 * Tags: Stack, Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Problem Description:
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */
public class Leetcode94 {

    // Time: O(n) | Space: O(h) where h is tree height (recursion stack)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
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
        Leetcode94 sol = new Leetcode94();

        // Test 1: root = [1,null,2,3] → [1,3,2]
        TreeNode root1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(sol.inorderTraversal(root1)); // [1, 3, 2]

        // Test 2: root = [] → []
        System.out.println(sol.inorderTraversal(null));   // []

        // Test 3: root = [1] → [1]
        System.out.println(sol.inorderTraversal(new TreeNode(1))); // [1]
    }
}
