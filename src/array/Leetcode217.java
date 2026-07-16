package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 * Difficulty: Easy
 * Tags: Array, Hash Table, Sorting
 * URL: https://leetcode.com/problems/contains-duplicate/
 *
 * Problem Description:
 * Given an integer array nums, return true if any value appears at least twice
 * in the array, and return false if every element is distinct.
 */
public class Leetcode217 {

    /**
     * Time: O(n)  — single pass through array
     * Space: O(n) — hash set stores up to n elements
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode217 sol = new Leetcode217();

        System.out.println("Test 1: " + sol.containsDuplicate(new int[] { 1, 2, 3, 1 }) + " | Expected: true");
        System.out.println("Test 2: " + sol.containsDuplicate(new int[] { 1, 2, 3, 4 }) + " | Expected: false");
        System.out.println("Test 3: " + sol.containsDuplicate(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }) + " | Expected: true");
    }
}
