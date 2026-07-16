package tree;

/**
 * 112. Path Sum
 * Difficulty: Easy
 * Tags: Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/path-sum/
 *
 * Problem Description:
 * Determine if the tree has a root-to-leaf path such that adding up all values equals targetSum.
 */
public class Leetcode112 {

    // Time: O(n) | Space: O(h) — DFS subtracting node value from targetSum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
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
        Leetcode112 sol = new Leetcode112();

        // Test 1: [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum=22 → true
        TreeNode r1 = new TreeNode(5,
                new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
        System.out.println(sol.hasPathSum(r1, 22));   // true

        // Test 2: [1,2,3], targetSum=5 → false
        TreeNode r2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sol.hasPathSum(r2, 5));    // false

        // Test 3: [] → false
        System.out.println(sol.hasPathSum(null, 0));  // false
    }
}
