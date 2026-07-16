package dp;

/**
 * 96. Unique Binary Search Trees
 * Difficulty: Medium
 * Tags: Math, Dynamic Programming, Tree, BST, Binary Tree
 * URL: https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Problem Description:
 * Given an integer n, return the number of structurally unique BSTs with exactly n nodes.
 */
public class Leetcode96 {
    /**
     * DP: Catalan numbers.
     * dp[n] = sum(dp[i-1] * dp[n-i]) for i = 1..n
     * Time: O(n^2), Space: O(n)
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode96 sol = new Leetcode96();
        System.out.println(sol.numTrees(3));   // Expected: 5
        System.out.println(sol.numTrees(1));   // Expected: 1
        System.out.println(sol.numTrees(4));   // Expected: 14
        System.out.println(sol.numTrees(19));  // Expected: 1767263190
    }
}
