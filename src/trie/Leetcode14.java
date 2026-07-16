package trie;

/**
 * 14. Longest Common Prefix
 * Difficulty: Easy
 * Tags: String, Trie
 * URL: https://leetcode.com/problems/longest-common-prefix/
 *
 * Problem Description:
 * Find the longest common prefix string amongst an array of strings.
 */
public class Leetcode14 {

    /**
     * Horizontal scanning: compare characters across all strings.
     * Time: O(S)    — S is sum of all characters
     * Space: O(1)   — only index variables
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        Leetcode14 sol = new Leetcode14();

        // Test 1
        System.out.println("Test 1: \"" + sol.longestCommonPrefix(new String[] { "flower", "flow", "flight" }) + "\" | Expected: \"fl\"");

        // Test 2
        System.out.println("Test 2: \"" + sol.longestCommonPrefix(new String[] { "dog", "racecar", "car" }) + "\" | Expected: \"\"");

        // Test 3
        System.out.println("Test 3: \"" + sol.longestCommonPrefix(new String[] { "a" }) + "\" | Expected: \"a\"");
    }
}
