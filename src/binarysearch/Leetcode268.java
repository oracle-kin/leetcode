package binarysearch;

/**
 * 268. Missing Number
 * Difficulty: Easy
 * Tags: Array, Hash Table, Math, Binary Search, Bit Manipulation, Sorting
 * URL: https://leetcode.com/problems/missing-number/
 *
 * Problem Description:
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Complexity: O(n) time, O(1) space
 * Algorithm: Using XOR. XOR all indices and all values. Since a XOR a = 0,
 * the remaining value is the missing number. Alternatively, use the sum formula:
 * missing = n*(n+1)/2 - sum(nums).
 */
public class Leetcode268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = n;

        for (int i = 0; i < n; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode268 sol = new Leetcode268();

        // Test case 1
        int[] nums1 = {3, 0, 1};
        System.out.println("Test 1: " + sol.missingNumber(nums1)); // Expected: 2

        // Test case 2
        int[] nums2 = {0, 1};
        System.out.println("Test 2: " + sol.missingNumber(nums2)); // Expected: 2

        // Test case 3
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Test 3: " + sol.missingNumber(nums3)); // Expected: 8

        // Test case 4
        int[] nums4 = {0};
        System.out.println("Test 4: " + sol.missingNumber(nums4)); // Expected: 1

        // Test case 5
        int[] nums5 = {1};
        System.out.println("Test 5: " + sol.missingNumber(nums5)); // Expected: 0
    }
}
