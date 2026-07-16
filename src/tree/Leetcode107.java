package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II
 * Difficulty: Medium
 * Tags: Tree, BFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 *
 * Problem Description:
 * Return the bottom-up level order traversal of a binary tree (from leaf to root).
 */
public class Leetcode107 {

    // Time: O(n) | Space: O(w) — BFS level-order then reverse the result list
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            result.add(0, level); // insert at front for bottom-up order
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
        Leetcode107 sol = new Leetcode107();

        // Test 1: [3,9,20,null,null,15,7] → [[15,7],[9,20],[3]]
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.levelOrderBottom(r1));

        // Test 2: [1] → [[1]]
        System.out.println(sol.levelOrderBottom(new TreeNode(1)));

        // Test 3: [] → []
        System.out.println(sol.levelOrderBottom(null));
    }
}
