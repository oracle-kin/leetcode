package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 * Problem Description:
 * Find the minimum depth of a binary tree (shortest path from root to nearest leaf).
 */
public class Leetcode111 {

    // Time: O(n) | Space: O(w) — BFS: return depth at first leaf encountered
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null) return depth;
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            depth++;
        }
        return depth; // unreachable
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
        Leetcode111 sol = new Leetcode111();

        // Test 1: [3,9,20,null,null,15,7] → 2
        TreeNode r1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.minDepth(r1)); // 2

        // Test 2: [2,null,3,null,4,null,5,null,6] → 5
        TreeNode r2 = new TreeNode(2, null,
                new TreeNode(3, null,
                        new TreeNode(4, null,
                                new TreeNode(5, null, new TreeNode(6)))));
        System.out.println(sol.minDepth(r2)); // 5

        // Test 3: [] → 0
        System.out.println(sol.minDepth(null)); // 0
    }
}
