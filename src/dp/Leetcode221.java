package dp;

/**
 * 221. Maximal Square
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Matrix
 * URL: https://leetcode.com/problems/maximal-square/
 *
 * Problem Description:
 * Find the largest square containing only 1's in a binary matrix and return its area.
 */
public class Leetcode221 {
    /**
     * DP: dp[i][j] = side length of largest square ending at (i,j).
     * If matrix[i][j]=='1': dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
     * Track max side, return maxSide^2.
     * Time: O(m*n), Space: O(n) with 1D optimization
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n + 1];
        int maxSide = 0, prev = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), prev);
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        Leetcode221 sol = new Leetcode221();
        char[][] m1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println(sol.maximalSquare(m1)); // Expected: 4

        char[][] m2 = {{'0', '1'}, {'1', '0'}};
        System.out.println(sol.maximalSquare(m2)); // Expected: 1

        char[][] m3 = {{'0'}};
        System.out.println(sol.maximalSquare(m3)); // Expected: 0
    }
}
