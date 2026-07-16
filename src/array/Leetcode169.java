package array;

/**
 * 169. Majority Element
 * Difficulty: Easy
 * Tags: Array, Hash Table, Divide and Conquer, Sorting, Counting
 * URL: https://leetcode.com/problems/majority-element/
 *
 * Problem Description:
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than n/2 times.
 * You may assume that the majority element always exists in the array.
 */
public class Leetcode169 {

    /**
     * Time: O(n)  — single pass through array
     * Space: O(1) — only two variables
     *
     * Approach: Boyer-Moore Majority Vote algorithm
     */
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        Leetcode169 sol = new Leetcode169();

        System.out.println("Test 1: " + sol.majorityElement(new int[] { 3, 2, 3 }) + " | Expected: 3");
        System.out.println("Test 2: " + sol.majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }) + " | Expected: 2");
        System.out.println("Test 3: " + sol.majorityElement(new int[] { 1 }) + " | Expected: 1");
    }
}
