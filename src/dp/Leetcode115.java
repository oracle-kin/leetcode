package dp;

/**
 * 115. Distinct Subsequences
 * Difficulty: Hard
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/distinct-subsequences/
 *
 * Problem Description:
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 */
public class Leetcode115 {
    /**
     * DP: dp[i][j] = number of subsequences of s[0..i-1] matching t[0..j-1].
     * dp[i][j] = dp[i-1][j] + (s[i-1]==t[j-1] ? dp[i-1][j-1] : 0)
     * Time: O(m*n), Space: O(n) with 1D optimization
     */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode115 sol = new Leetcode115();
        System.out.println(sol.numDistinct("rabbbit", "rabbit")); // Expected: 3
        System.out.println(sol.numDistinct("babgbag", "bag"));    // Expected: 5
        System.out.println(sol.numDistinct("", "a"));             // Expected: 0
        System.out.println(sol.numDistinct("a", ""));             // Expected: 1
    }
}
