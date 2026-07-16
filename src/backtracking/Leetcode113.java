package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 * Difficulty: Medium
 * Tags: Tree, DFS, Backtracking, Binary Tree
 * URL: https://leetcode.com/problems/path-sum-ii/
 *
 * Problem Description:
 * Return all root-to-leaf paths where the sum equals targetSum.
 *
 * Complexity: O(N) time, O(H) recursion stack where H = tree height.
 */
public class Leetcode113 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), root, targetSum);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> path, TreeNode node, int remain) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null && remain == node.val) {
            result.add(new ArrayList<>(path));
        } else {
            backtrack(result, path, node.left, remain - node.val);
            backtrack(result, path, node.right, remain - node.val);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Leetcode113 sol = new Leetcode113();

        // Test 1: [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum=22 => [[5,4,11,2],[5,8,4,5]]
        TreeNode root = new TreeNode(5,
            new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
            new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1)))
        );
        System.out.println("Test 1: " + sol.pathSum(root, 22));

        // Test 2: [1,2,3], targetSum=5 => []
        root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 2: " + sol.pathSum(root, 5));
    }
}
