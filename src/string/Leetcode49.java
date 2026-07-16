package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams
 * Difficulty: Medium
 * Tags: Array, Hash Table, String, Sorting
 * URL: https://leetcode.com/problems/group-anagrams/
 *
 * Problem Description:
 * Group the anagrams together from an array of strings.
 */
public class Leetcode49 {

    /**
     * Sort each string as key; group by sorted key.
     * Time: O(n * k log k) — n strings, each of length k
     * Space: O(n * k)      — hash map stores all strings
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Leetcode49 sol = new Leetcode49();

        // Test 1
        List<List<String>> res1 = sol.groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
        System.out.println("Test 1: " + res1);
        System.out.println("Expected: [[eat, tea, ate], [bat], [tan, nat]] (order may vary)");

        // Test 2
        System.out.println("Test 2: " + sol.groupAnagrams(new String[] { "" }));
        System.out.println("Expected: [[]]");

        // Test 3
        System.out.println("Test 3: " + sol.groupAnagrams(new String[] { "a" }));
        System.out.println("Expected: [[a]]");
    }
}
