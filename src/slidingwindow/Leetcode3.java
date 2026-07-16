package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 * Difficulty: Medium
 * Tags: Hash Table, String, Sliding Window
 * URL: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Problem Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 */
public class Leetcode3 {

    /**
     * Sliding window with HashSet. Right pointer expands, left contracts on duplicate.
     * Time: O(n)  — each char added/removed at most once
     * Space: O(k) — k is size of character set (at most 26 lowercase, but general O(min(n,m)))
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Leetcode3 sol = new Leetcode3();

        // Test 1
        System.out.println("Test 1: " + sol.lengthOfLongestSubstring("abcabcbb") + " | Expected: 3");

        // Test 2
        System.out.println("Test 2: " + sol.lengthOfLongestSubstring("bbbbb") + " | Expected: 1");

        // Test 3
        System.out.println("Test 3: " + sol.lengthOfLongestSubstring("pwwkew") + " | Expected: 3");

        // Test 4
        System.out.println("Test 4: " + sol.lengthOfLongestSubstring("") + " | Expected: 0");
    }
}
