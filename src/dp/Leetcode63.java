package dp;

/**
 * 63. Unique Paths II
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Matrix
 * URL: https://leetcode.com/problems/unique-paths-ii/
 *
 * Problem Description:
 * Same as Unique Paths but with obstacles. Return the number of unique paths.
 */
public class Leetcode63 {
    /**
     * DP | O(m*n) time, O(n) space
     * dp[j] = 0 if obstacle, else dp[j] += dp[j-1]
     * Handle first cell and first row specially.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Leetcode63 sol = new Leetcode63();
        int[][] grid1 = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(sol.uniquePathsWithObstacles(grid1)); // 2
        int[][] grid2 = {{0,1},{0,0}};
        System.out.println(sol.uniquePathsWithObstacles(grid2)); // 1
        int[][] grid3 = {{1,0}};
        System.out.println(sol.uniquePathsWithObstacles(grid3)); // 0
    }
}
