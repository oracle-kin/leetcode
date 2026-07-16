package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Isomorphic Strings
 * Difficulty: Easy
 * Tags: Hash Table, String
 * URL: https://leetcode.com/problems/isomorphic-strings/
 *
 * Problem Description:
 * Determine if two strings are isomorphic (characters can be replaced to get the other string).
 */
public class Leetcode205 {

    /**
     * Two maps to ensure bijection between characters.
     * Time: O(n)  — single pass
     * Space: O(1) — at most 128 ASCII entries
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (mapST.containsKey(c1) && mapST.get(c1) != c2) return false;
            if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) return false;
            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }

        return true;
    }

    public static void main(String[] args) {
        Leetcode205 sol = new Leetcode205();

        // Test 1
        System.out.println("Test 1: " + sol.isIsomorphic("egg", "add") + " | Expected: true");

        // Test 2
        System.out.println("Test 2: " + sol.isIsomorphic("foo", "bar") + " | Expected: false");

        // Test 3
        System.out.println("Test 3: " + sol.isIsomorphic("paper", "title") + " | Expected: true");

        // Test 4
        System.out.println("Test 4: " + sol.isIsomorphic("badc", "baba") + " | Expected: false");
    }
}
