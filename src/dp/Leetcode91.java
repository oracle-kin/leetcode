package dp;

/**
 * 91. Decode Ways
 * Difficulty: Medium
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/decode-ways/
 *
 * Problem Description:
 * Given a string containing only digits, return the number of ways to decode it.
 */
public class Leetcode91 {
    /**
     * DP | O(n) time, O(1) space
     * dp[i] = number of ways to decode s[0..i)
     * dp[i] += dp[i-1] if s[i-1] is '1'-'9'
     * dp[i] += dp[i-2] if s[i-2..i-1] is "10"-"26"
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int n = s.length();
        int prev2 = 1, prev1 = 1;
        for (int i = 1; i < n; i++) {
            int cur = 0;
            char c = s.charAt(i);
            if (c != '0') cur += prev1;
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) cur += prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Leetcode91 sol = new Leetcode91();
        System.out.println(sol.numDecodings("12"));   // 2
        System.out.println(sol.numDecodings("226"));  // 3
        System.out.println(sol.numDecodings("06"));   // 0
        System.out.println(sol.numDecodings("10"));   // 1
        System.out.println(sol.numDecodings("11106"));// 2
    }
}
