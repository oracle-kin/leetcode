package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * Difficulty: Medium
 * Tags: Array, Hash Table, Union Find
 * URL: https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Problem Description:
 * Find the length of the longest consecutive elements sequence in an unsorted array in O(n).
 */
public class Leetcode128 {

    /**
     * Time: O(n)  — each number is visited at most twice (once in set build, once in while loop)
     * Space: O(n) — hash set stores all numbers
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLen = 0;
        for (int num : set) {
            // Only start counting if num is the start of a sequence
            if (!set.contains(num - 1)) {
                int current = num;
                int len = 1;
                while (set.contains(current + 1)) {
                    current++;
                    len++;
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Leetcode128 sol = new Leetcode128();

        // Test 1
        int res1 = sol.longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 });
        System.out.println("Test 1: " + res1 + " | Expected: 4");

        // Test 2
        int res2 = sol.longestConsecutive(new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 });
        System.out.println("Test 2: " + res2 + " | Expected: 9");

        // Test 3
        int res3 = sol.longestConsecutive(new int[] { });
        System.out.println("Test 3: " + res3 + " | Expected: 0");
    }
}
