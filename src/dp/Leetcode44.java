package dp;

/**
 * 44. Wildcard Matching
 * Difficulty: Hard
 * Tags: String, Dynamic Programming, Greedy, Recursion
 * URL: https://leetcode.com/problems/wildcard-matching/
 * 
 * Problem Description:
 * Implement wildcard pattern matching with support for '?' and '*'.
 */
public class Leetcode44 {
    /**
     * DP | O(m*n) time, O(m*n) space (optimizable to O(n))
     * dp[i][j] = whether s[0..i) matches p[0..j)
     * dp[0][0] = true
     * If p[j-1] == '*', dp[i][j] = dp[i-1][j] (match char) || dp[i][j-1] (match empty)
     * Else, dp[i][j] = match(s[i-1], p[j-1]) && dp[i-1][j-1]
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = (pc == '?' || pc == sc) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Leetcode44 sol = new Leetcode44();
        System.out.println(sol.isMatch("aa", "a"));     // false
        System.out.println(sol.isMatch("aa", "*"));     // true
        System.out.println(sol.isMatch("cb", "?a"));    // false
        System.out.println(sol.isMatch("adceb", "*a*b")); // true
        System.out.println(sol.isMatch("acdcb", "a*c?b")); // false
    }
}
