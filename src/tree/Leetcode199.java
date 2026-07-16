package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View
 * Difficulty: Medium
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Problem Description:
 * Return the values of the nodes you can see ordered from top to bottom when looking from the right side.
 */
public class Leetcode199 {

    // Time: O(n) | Space: O(w) — BFS: last node of each level is the right-side view
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == size - 1) result.add(cur.val); // last node in level
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
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
        Leetcode199 sol = new Leetcode199();

        // Test 1: [1,2,3,null,5,null,4] → [1,3,4]
        TreeNode r1 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));
        System.out.println(sol.rightSideView(r1)); // [1, 3, 4]

        // Test 2: [1,null,3] → [1,3]
        TreeNode r2 = new TreeNode(1, null, new TreeNode(3));
        System.out.println(sol.rightSideView(r2)); // [1, 3]

        // Test 3: [] → []
        System.out.println(sol.rightSideView(null)); // []
    }
}
