package dp;

/**
 * 5. Longest Palindromic Substring
 * Difficulty: Medium
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * Problem Description:
 * Given a string s, return the longest palindromic substring in s.
 */
public class Leetcode5 {
    /**
     * Expand Around Center | O(n^2) time, O(1) space
     * For each index i, expand around i (odd-length palindrome) and around i,i+1 (even-length).
     * Track the longest palindrome seen.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        Leetcode5 sol = new Leetcode5();
        System.out.println(sol.longestPalindrome("babad")); // "bab" or "aba"
        System.out.println(sol.longestPalindrome("cbbd"));  // "bb"
        System.out.println(sol.longestPalindrome("a"));     // "a"
        System.out.println(sol.longestPalindrome("ac"));    // "a" or "c"
        System.out.println(sol.longestPalindrome("racecar"));// "racecar"
    }
}
