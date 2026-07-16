package dp;

/**
 * 64. Minimum Path Sum
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Matrix
 * URL: https://leetcode.com/problems/minimum-path-sum/
 *
 * Problem Description:
 * Find a path from top-left to bottom-right which minimizes the sum of all numbers along its path.
 */
public class Leetcode64 {
    /**
     * DP | O(m*n) time, O(n) space
     * dp[j] = grid[i][j] + min(dp[j] (from above), dp[j-1] (from left))
     * Handle first row by accumulating, handle first column separately.
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) dp[j] = dp[j - 1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Leetcode64 sol = new Leetcode64();
        int[][] grid1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(sol.minPathSum(grid1)); // 7
        int[][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println(sol.minPathSum(grid2)); // 12
        int[][] grid3 = {{1}};
        System.out.println(sol.minPathSum(grid3)); // 1
    }
}
