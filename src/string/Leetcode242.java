package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram
 * Difficulty: Easy
 * Tags: Hash Table, String, Sorting
 * URL: https://leetcode.com/problems/valid-anagram/
 *
 * Problem Description:
 * Determine if two strings are anagrams of each other.
 */
public class Leetcode242 {

    /**
     * Frequency counter using HashMap.
     * Time: O(n)  — single pass through both strings
     * Space: O(k) — k unique characters
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            if (count == 0) return false;
            map.put(c, count - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        Leetcode242 sol = new Leetcode242();

        // Test 1
        System.out.println("Test 1: " + sol.isAnagram("anagram", "nagaram") + " | Expected: true");

        // Test 2
        System.out.println("Test 2: " + sol.isAnagram("rat", "car") + " | Expected: false");

        // Test 3
        System.out.println("Test 3: " + sol.isAnagram("a", "ab") + " | Expected: false");

        // Test 4
        System.out.println("Test 4: " + sol.isAnagram("aacc", "ccac") + " | Expected: false");
    }
}
