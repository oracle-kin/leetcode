package dp;

/**
 * 10. Regular Expression Matching
 * Difficulty: Hard
 * Tags: String, Dynamic Programming, Recursion
 * URL: https://leetcode.com/problems/regular-expression-matching/
 * 
 * Problem Description:
 * Implement regular expression matching with support for '.' and '*'.
 */
public class Leetcode10 {
    /**
     * DP | O(m*n) time, O(m*n) space
     * dp[i][j] = whether s[0..i) matches p[0..j)
     * dp[0][0] = true (empty matches empty)
     * If p[j-1] == '*', dp[i][j] = dp[i][j-2] (zero of preceding) ||
     *   (matches(s[i-1], p[j-2]) && dp[i-1][j])
     * Else, dp[i][j] = matches(s[i-1], p[j-1]) && dp[i-1][j-1]
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 2] ||
                        ((p.charAt(j - 2) == '.' || p.charAt(j - 2) == sc) && dp[i - 1][j]);
                } else {
                    dp[i][j] = (pc == '.' || pc == sc) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Leetcode10 sol = new Leetcode10();
        System.out.println(sol.isMatch("aa", "a"));      // false
        System.out.println(sol.isMatch("aa", "a*"));     // true
        System.out.println(sol.isMatch("ab", ".*"));     // true
        System.out.println(sol.isMatch("aab", "c*a*b")); // true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
