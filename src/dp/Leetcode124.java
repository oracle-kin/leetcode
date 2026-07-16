package dp;

/**
 * 124. Binary Tree Maximum Path Sum
 * Difficulty: Hard
 * Tags: Dynamic Programming, Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Problem Description:
 * Find the maximum path sum of any non-empty path in a binary tree.
 */
public class Leetcode124 {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    private int maxSum;

    /**
     * DFS: compute max path sum that passes through each node (as root),
     * update global max with left+right+node (full path),
     * return max single branch (node + max(left,right)) to parent.
     * Time: O(n), Space: O(h)
     */
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        maxSum = Math.max(maxSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        Leetcode124 sol = new Leetcode124();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println(sol.maxPathSum(root1)); // Expected: 6

        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println(sol.maxPathSum(root2)); // Expected: 42

        TreeNode root3 = new TreeNode(-3);
        System.out.println(sol.maxPathSum(root3)); // Expected: -3
    }
}
