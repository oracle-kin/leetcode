package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * Difficulty: Hard
 * Tags: Hash Table, String, Sliding Window
 * URL: https://leetcode.com/problems/minimum-window-substring/
 *
 * Problem Description:
 * Find the minimum window substring of s such that every character in t is included.
 */
public class Leetcode76 {

    /**
     * Sliding window with two frequency maps. Expand right until valid window,
     * then contract left to find minimum.
     * Time: O(m + n) — m = s.length(), n = t.length()
     * Space: O(k)   — k is size of character set
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, minStart = 0, minLen = Integer.MAX_VALUE;
        int matched = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (target.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(target.get(c))) {
                    matched++;
                }
            }

            while (matched == target.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                if (target.containsKey(leftChar)) {
                    if (window.get(leftChar).equals(target.get(leftChar))) {
                        matched--;
                    }
                    window.put(leftChar, window.get(leftChar) - 1);
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        Leetcode76 sol = new Leetcode76();

        // Test 1
        System.out.println("Test 1: \"" + sol.minWindow("ADOBECODEBANC", "ABC") + "\" | Expected: \"BANC\"");

        // Test 2
        System.out.println("Test 2: \"" + sol.minWindow("a", "a") + "\" | Expected: \"a\"");

        // Test 3
        System.out.println("Test 3: \"" + sol.minWindow("a", "aa") + "\" | Expected: \"\"");
    }
}
