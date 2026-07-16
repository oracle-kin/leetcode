package dp;

import java.util.*;

/**
 * 132. Palindrome Partitioning II
 * Difficulty: Hard
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * Problem Description:
 * Return the minimum cuts needed for a palindrome partitioning of s.
 */
public class Leetcode132 {
    /**
     * DP: dp[i] = min cuts for s[0..i-1]. For each center, expand palindrome
     * and update dp[j+1] = min(dp[j+1], dp[i] + 1) where s[i..j] is palindrome.
     * Time: O(n^2), Space: O(n)
     */
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) dp[i] = i - 1;

        for (int center = 0; center < n; center++) {
            // Odd-length palindrome
            for (int l = center, r = center; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
            // Even-length palindrome
            for (int l = center, r = center + 1; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode132 sol = new Leetcode132();
        System.out.println(sol.minCut("aab"));  // Expected: 1
        System.out.println(sol.minCut("a"));    // Expected: 0
        System.out.println(sol.minCut("ab"));   // Expected: 1
    }
}
