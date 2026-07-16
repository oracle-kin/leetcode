package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 144. Binary Tree Preorder Traversal
 * Difficulty: Easy
 * Tags: Stack, Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * Problem Description:
 * Return the preorder traversal of a binary tree.
 */
public class Leetcode144 {

    // Time: O(n) | Space: O(h) — iterative stack-based preorder (root → left → right)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left  != null) stack.push(cur.left);
        }
        return result;
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
        Leetcode144 sol = new Leetcode144();

        // Test 1: [1,null,2,3] → [1,2,3]
        TreeNode r1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(sol.preorderTraversal(r1)); // [1, 2, 3]

        // Test 2: [] → []
        System.out.println(sol.preorderTraversal(null)); // []

        // Test 3: [1,2,3,4,5] → [1,2,4,5,3]
        TreeNode r3 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        System.out.println(sol.preorderTraversal(r3)); // [1, 2, 4, 5, 3]
    }
}
