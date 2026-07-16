package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 145. Binary Tree Postorder Traversal
 * Difficulty: Easy
 * Tags: Stack, Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Problem Description:
 * Return the postorder traversal of a binary tree.
 */
public class Leetcode145 {

    // Time: O(n) | Space: O(h) — iterative postorder using two stacks or reverse-preorder trick
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(0, cur.val); // add to front = reverse of (root → right → left)
            if (cur.left  != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
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
        Leetcode145 sol = new Leetcode145();

        // Test 1: [1,null,2,3] → [3,2,1]
        TreeNode r1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(sol.postorderTraversal(r1)); // [3, 2, 1]

        // Test 2: [] → []
        System.out.println(sol.postorderTraversal(null)); // []

        // Test 3: [1,2,3,4,5] → [4,5,2,3,1]
        TreeNode r3 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        System.out.println(sol.postorderTraversal(r3)); // [4, 5, 2, 3, 1]
    }
}
