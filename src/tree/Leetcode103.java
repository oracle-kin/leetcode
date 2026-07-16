package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Difficulty: Medium
 * Tags: Tree, BFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Problem Description:
 * Return the zigzag level order traversal of a binary tree (alternating left-to-right and right-to-left).
 */
public class Leetcode103 {

    // Time: O(n) | Space: O(w) — BFS with direction toggle per level
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (leftToRight) level.addLast(cur.val);
                else             level.addFirst(cur.val);
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
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
        Leetcode103 sol = new Leetcode103();

        // Test 1: [3,9,20,null,null,15,7] → [[3],[20,9],[15,7]]
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.zigzagLevelOrder(r1));

        // Test 2: [1] → [[1]]
        System.out.println(sol.zigzagLevelOrder(new TreeNode(1)));

        // Test 3: [] → []
        System.out.println(sol.zigzagLevelOrder(null));
    }
}
