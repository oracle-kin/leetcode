package bit;

/**
 * 137. Single Number II
 * Difficulty: Medium
 * Tags: Array, Bit Manipulation
 * URL: https://leetcode.com/problems/single-number-ii/
 *
 * Problem Description:
 * Given an integer array where every element appears three times except one,
 * find the element that appears exactly once.
 *
 * Complexity: O(n) time, O(1) space
 * Algorithm: Use two bitmasks (ones, twos) to track bits that have appeared
 * once and twice. When a bit appears three times, it gets cleared from both.
 * After processing all numbers, 'ones' holds the single number.
 */
public class Leetcode137 {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            // Update ones: bits that appear once
            ones = (ones ^ num) & ~twos;
            // Update twos: bits that appear twice
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }

    public static void main(String[] args) {
        Leetcode137 sol = new Leetcode137();

        // Test case 1
        int[] nums1 = {2, 2, 3, 2};
        System.out.println("Test 1: " + sol.singleNumber(nums1)); // Expected: 3

        // Test case 2
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("Test 2: " + sol.singleNumber(nums2)); // Expected: 99

        // Test case 3
        int[] nums3 = {30000, 500, 100, 30000, 100, 30000, 100};
        System.out.println("Test 3: " + sol.singleNumber(nums3)); // Expected: 500

        // Test case 4
        int[] nums4 = {-2, -2, 1, 1, 4, 1, 4, 4, -2, -2};
        // Actually: let's use a proper test: nums4 = {-2,-2,1,1,4,1,4,4,-2,-2} is invalid (each appears 3x or 1x)
        // Let's correct:
        int[] nums4fixed = {-2, -2, -2, 5};
        System.out.println("Test 4: " + sol.singleNumber(nums4fixed)); // Expected: 5

        // Test case 5
        int[] nums5 = {1};
        System.out.println("Test 5: " + sol.singleNumber(nums5)); // Expected: 1
    }
}
