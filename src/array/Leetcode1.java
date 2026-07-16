package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * 1. Two Sum
 * Difficulty: Easy
 * Tags: Array, Hash Table
 * URL: https://leetcode.com/problems/two-sum/
 *
 * Problem Description:
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class Leetcode1 {

    /**
     * Time: O(n)  — single pass through the array
     * Space: O(n) — hash map stores up to n entries
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 }; // unreachable per problem constraints
    }

    public static void main(String[] args) {
        Leetcode1 sol = new Leetcode1();

        // Test 1
        int[] res1 = sol.twoSum(new int[] { 2, 7, 11, 15 }, 9);
        System.out.println("Test 1: " + Arrays.toString(res1) + " | Expected: [0, 1]");

        // Test 2
        int[] res2 = sol.twoSum(new int[] { 3, 2, 4 }, 6);
        System.out.println("Test 2: " + Arrays.toString(res2) + " | Expected: [1, 2]");

        // Test 3
        int[] res3 = sol.twoSum(new int[] { 3, 3 }, 6);
        System.out.println("Test 3: " + Arrays.toString(res3) + " | Expected: [0, 1]");
    }
}
