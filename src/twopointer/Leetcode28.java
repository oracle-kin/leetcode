package twopointer;

/**
 * 28. Find the Index of the First Occurrence in a String
 * Difficulty: Easy
 * Tags: Two Pointers, String, String Matching
 * URL: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 *
 * Problem Description:
 * Return the index of the first occurrence of needle in haystack, or -1.
 *
 * Time: O(n * m) | Space: O(1)
 * where n = haystack length, m = needle length
 */
public class Leetcode28 {

    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) return 0;

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Leetcode28 sol = new Leetcode28();

        // Test case 1
        System.out.println("Test 1: " + sol.strStr("sadbutsad", "sad"));
        // Expected: 0

        // Test case 2
        System.out.println("Test 2: " + sol.strStr("leetcode", "leeto"));
        // Expected: -1

        // Test case 3
        System.out.println("Test 3: " + sol.strStr("hello", "ll"));
        // Expected: 2
    }
}
