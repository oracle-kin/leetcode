package dp;

/**
 * 32. Longest Valid Parentheses
 * Difficulty: Hard
 * Tags: String, Dynamic Programming, Stack
 * URL: https://leetcode.com/problems/longest-valid-parentheses/
 * 
 * Problem Description:
 * Find the length of the longest valid (well-formed) parentheses substring.
 */
public class Leetcode32 {
    /**
     * DP | O(n) time, O(n) space
     * dp[i] = length of longest valid parentheses ending at i
     * If s[i] == '(' -> dp[i] = 0
     * If s[i] == ')':
     *   if s[i-1] == '(' -> dp[i] = dp[i-2] + 2
     *   if s[i-1] == ')' and s[i-dp[i-1]-1] == '(' -> dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Leetcode32 sol = new Leetcode32();
        System.out.println(sol.longestValidParentheses("(()"));    // 2
        System.out.println(sol.longestValidParentheses(")()())")); // 4
        System.out.println(sol.longestValidParentheses(""));       // 0
        System.out.println(sol.longestValidParentheses("()(()"));  // 2
        System.out.println(sol.longestValidParentheses("()(())")); // 6
    }
}
