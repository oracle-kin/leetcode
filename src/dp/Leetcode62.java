package dp;

/**
 * 62. Unique Paths
 * Difficulty: Medium
 * Tags: Math, Dynamic Programming, Combinatorics
 * URL: https://leetcode.com/problems/unique-paths/
 *
 * Problem Description:
 * A robot is at the top-left corner of an m x n grid. How many possible unique paths to the bottom-right corner?
 */
public class Leetcode62 {
    /**
     * DP / Combinatorics | O(m*n) time (or O(min(m,n)) with math), O(n) space
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * Optimized to 1D DP: dp[j] += dp[j-1]
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) dp[j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Leetcode62 sol = new Leetcode62();
        System.out.println(sol.uniquePaths(3, 7));  // 28
        System.out.println(sol.uniquePaths(3, 2));  // 3
        System.out.println(sol.uniquePaths(1, 1));  // 1
        System.out.println(sol.uniquePaths(10, 10)); // 48620
    }
}
