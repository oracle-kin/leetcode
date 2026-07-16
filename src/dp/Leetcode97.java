package dp;

/**
 * 97. Interleaving String
 * Difficulty: Medium
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/interleaving-string/
 *
 * Problem Description:
 * Determine whether s3 is formed by an interleaving of s1 and s2.
 */
public class Leetcode97 {
    /**
     * DP: dp[i][j] = true if s1[0..i-1] and s2[0..j-1] interleave to s3[0..i+j-1].
     * dp[i][j] = (s1[i-1]==s3[i+j-1] && dp[i-1][j]) || (s2[j-1]==s3[i+j-1] && dp[i][j-1])
     * Time: O(m*n), Space: O(m*n), can optimize to O(n)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                     || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode97 sol = new Leetcode97();
        System.out.println(sol.isInterleave("aabcc", "dbbca", "aadbbcbcac")); // Expected: true
        System.out.println(sol.isInterleave("aabcc", "dbbca", "aadbbbaccc")); // Expected: false
        System.out.println(sol.isInterleave("", "", "")); // Expected: true
        System.out.println(sol.isInterleave("a", "", "a")); // Expected: true
    }
}
