package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Difficulty: Medium
 * Tags: Array, Hash Table, Divide and Conquer, Tree, Binary Tree
 * URL: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Problem Description:
 * Given inorder and postorder traversal arrays, construct and return the binary tree.
 */
public class Leetcode106 {

    // Time: O(n) | Space: O(n) — hashmap for O(1) inorder-index lookup + recursion from postorder tail
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return build(postorder, 0, postorder.length - 1,
                     inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode build(int[] post, int pL, int pR,
                           int[] in, int iL, int iR,
                           Map<Integer, Integer> inMap) {
        if (pL > pR || iL > iR) return null;
        TreeNode root = new TreeNode(post[pR]);
        int inIdx = inMap.get(root.val);
        int rightSize = iR - inIdx;
        root.left  = build(post, pL, pR - rightSize - 1, in, iL, inIdx - 1, inMap);
        root.right = build(post, pR - rightSize, pR - 1, in, inIdx + 1, iR, inMap);
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
        Leetcode106 sol = new Leetcode106();

        // Test: inorder=[9,3,15,20,7] postorder=[9,15,7,20,3]
        TreeNode root = sol.buildTree(
                new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        System.out.println(root.val);                          // 3
        System.out.println(root.left.val);                     // 9
        System.out.println(root.right.val);                    // 20
        System.out.println(root.right.left.val);               // 15
        System.out.println(root.right.right.val);              // 7
    }
}
