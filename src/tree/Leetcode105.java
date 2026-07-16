package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Difficulty: Medium
 * Tags: Array, Hash Table, Divide and Conquer, Tree, Binary Tree
 * URL: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Problem Description:
 * Given preorder and inorder traversal arrays, construct and return the binary tree.
 */
public class Leetcode105 {

    // Time: O(n) | Space: O(n) — hashmap for O(1) inorder-index lookup + recursion
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1,
                     inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode build(int[] pre, int pL, int pR,
                           int[] in, int iL, int iR,
                           Map<Integer, Integer> inMap) {
        if (pL > pR || iL > iR) return null;
        TreeNode root = new TreeNode(pre[pL]);
        int inIdx = inMap.get(root.val);
        int leftSize = inIdx - iL;
        root.left  = build(pre, pL + 1, pL + leftSize, in, iL, inIdx - 1, inMap);
        root.right = build(pre, pL + leftSize + 1, pR, in, inIdx + 1, iR, inMap);
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
        Leetcode105 sol = new Leetcode105();

        // Test: preorder=[3,9,20,15,7] inorder=[9,3,15,20,7]
        TreeNode root = sol.buildTree(
                new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root.val);                          // 3
        System.out.println(root.left.val);                     // 9
        System.out.println(root.right.val);                    // 20
        System.out.println(root.right.left.val);               // 15
        System.out.println(root.right.right.val);              // 7
    }
}
