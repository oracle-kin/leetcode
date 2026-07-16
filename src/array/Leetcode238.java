package array;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 * Difficulty: Medium
 * Tags: Array, Prefix Sum
 * URL: https://leetcode.com/problems/product-of-array-except-self/
 *
 * Problem Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal
 * to the product of all the elements of nums except nums[i]. You must write an
 * algorithm that runs in O(n) time and without using the division operation.
 */
public class Leetcode238 {

    /**
     * Time: O(n)  — two passes through array
     * Space: O(1) — excluding output array
     *
     * Approach: Compute prefix products in output array, then multiply
     * by suffix products on the second pass.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Prefix products: answer[i] = product of all elements to the left of i
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Suffix products: multiply by product of all elements to the right of i
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        Leetcode238 sol = new Leetcode238();

        System.out.println("Test 1: " + Arrays.toString(sol.productExceptSelf(new int[] { 1, 2, 3, 4 }))
                + " | Expected: [24, 12, 8, 6]");
        System.out.println("Test 2: " + Arrays.toString(sol.productExceptSelf(new int[] { -1, 1, 0, -3, 3 }))
                + " | Expected: [0, 0, 9, 0, 0]");
    }
}
