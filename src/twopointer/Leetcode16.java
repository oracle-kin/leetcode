package twopointer;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Sorting
 * URL: https://leetcode.com/problems/3sum-closest/
 *
 * Problem Description:
 * Given an array nums and an integer target, find three integers in nums such that the sum is closest to target.
 *
 * Time: O(n^2) | Space: O(1)
 */
public class Leetcode16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // exact match
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        Leetcode16 sol = new Leetcode16();

        // Test case 1
        int[] nums1 = {-1, 2, 1, -4};
        System.out.println("Test 1: " + sol.threeSumClosest(nums1, 1));
        // Expected: 2 (sum = -1 + 2 + 1 = 2)

        // Test case 2
        int[] nums2 = {0, 0, 0};
        System.out.println("Test 2: " + sol.threeSumClosest(nums2, 1));
        // Expected: 0

        // Test case 3
        int[] nums3 = {1, 1, 1, 0};
        System.out.println("Test 3: " + sol.threeSumClosest(nums3, -100));
        // Expected: 2
    }
}
