package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 * Difficulty: Medium
 * Tags: Dynamic Programming, Backtracking, Tree, BST, Binary Tree
 * URL: https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * Problem Description:
 * Return all structurally unique BSTs with exactly n nodes of unique values from 1 to n.
 */
public class Leetcode95 {

    public static class TreeNode {
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

    /**
     * Recursive / DP | O(Catalan(n) * n) time, O(Catalan(n) * n) space
     * For each root i in [start, end], recursively generate all left subtrees from [start, i-1]
     * and all right subtrees from [i+1, end]. Combine each left with each right.
     * Use memoization to cache results per (start, end) pair.
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generate(1, n, new List[n + 2][n + 2]);
    }

    @SuppressWarnings("unchecked")
    private List<TreeNode> generate(int start, int end, List<TreeNode>[][] memo) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        if (memo[start][end] != null) return memo[start][end];
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generate(start, i - 1, memo);
            List<TreeNode> rightTrees = generate(i + 1, end, memo);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        memo[start][end] = result;
        return result;
    }

    // Helper to print tree (preorder traversal) for verification
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        Leetcode95 sol = new Leetcode95();
        List<TreeNode> trees1 = sol.generateTrees(3);
        System.out.println("n=3 -> " + trees1.size() + " trees"); // 5
        for (TreeNode root : trees1) {
            printTree(root);
            System.out.println();
        }
        List<TreeNode> trees2 = sol.generateTrees(1);
        System.out.println("n=1 -> " + trees2.size() + " trees"); // 1
        List<TreeNode> trees0 = sol.generateTrees(0);
        System.out.println("n=0 -> " + trees0.size() + " trees"); // 0
    }
}
