package dp;

/**
 * 72. Edit Distance
 * Difficulty: Medium
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/edit-distance/
 *
 * Problem Description:
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 */
public class Leetcode72 {
    /**
     * Edit Distance DP (Levenshtein) | O(m*n) time, O(n) space
     * dp[i][j] = min operations to convert word1[0..i) to word2[0..j)
     * If chars match: dp[i][j] = dp[i-1][j-1] (no operation)
     * Else: dp[i][j] = 1 + min(insert, delete, replace)
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) dp[j] = j;
        for (int i = 1; i <= m; i++) {
            int prev = dp[0]; // dp[i-1][j-1]
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), prev);
                }
                prev = temp;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode72 sol = new Leetcode72();
        System.out.println(sol.minDistance("horse", "ros"));       // 3
        System.out.println(sol.minDistance("intention", "execution")); // 5
        System.out.println(sol.minDistance("", "a"));               // 1
        System.out.println(sol.minDistance("abc", "abc"));          // 0
    }
}
