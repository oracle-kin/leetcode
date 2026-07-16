package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * Difficulty: Medium
 * Tags: Tree, BFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Problem Description:
 * Return the level order traversal of a binary tree's nodes' values (left to right, level by level).
 */
public class Leetcode102 {

    // Time: O(n) | Space: O(w) — BFS level-order traversal using a queue
    public List<List<Integer>> levelOrder(TreeNode root) {
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
            result.add(level);
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
        Leetcode102 sol = new Leetcode102();

        // Test 1: [3,9,20,null,null,15,7] → [[3],[9,20],[15,7]]
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.levelOrder(r1));

        // Test 2: [1] → [[1]]
        System.out.println(sol.levelOrder(new TreeNode(1)));

        // Test 3: [] → []
        System.out.println(sol.levelOrder(null));
    }
}
