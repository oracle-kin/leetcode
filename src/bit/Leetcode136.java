package bit;

/**
 * 136. Single Number
 * Difficulty: Easy
 * Tags: Array, Bit Manipulation
 * URL: https://leetcode.com/problems/single-number/
 *
 * Problem Description:
 * Given a non-empty array where every element appears twice except one,
 * find the element that appears only once.
 *
 * Complexity: O(n) time, O(1) space
 * Algorithm: XOR all elements. Since a XOR a = 0 and a XOR 0 = a,
 * all duplicate pairs cancel out, leaving only the single number.
 */
public class Leetcode136 {
    public int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode136 sol = new Leetcode136();

        // Test case 1
        int[] nums1 = {2, 2, 1};
        System.out.println("Test 1: " + sol.singleNumber(nums1)); // Expected: 1

        // Test case 2
        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Test 2: " + sol.singleNumber(nums2)); // Expected: 4

        // Test case 3
        int[] nums3 = {1};
        System.out.println("Test 3: " + sol.singleNumber(nums3)); // Expected: 1

        // Test case 4
        int[] nums4 = {-1, -1, -2};
        System.out.println("Test 4: " + sol.singleNumber(nums4)); // Expected: -2

        // Test case 5
        int[] nums5 = {0, 1, 0};
        System.out.println("Test 5: " + sol.singleNumber(nums5)); // Expected: 1
    }
}
