package binarysearch;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Problem Description:
 * Find the starting and ending position of a given target value in a sorted array.
 * Return [-1, -1] if target is not found.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Two binary searches — one to find the first occurrence (leftmost)
 * and one to find the last occurrence (rightmost) of the target.
 */
public class Leetcode34 {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        // Find first occurrence
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return result;
        }
        result[0] = left;

        // Find last occurrence
        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1; // Bias to the right
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        result[1] = right;

        return result;
    }

    public static void main(String[] args) {
        Leetcode34 sol = new Leetcode34();

        // Test case 1
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        System.out.println("Test 1: " + Arrays.toString(sol.searchRange(nums1, 8))); // Expected: [3, 4]

        // Test case 2
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        System.out.println("Test 2: " + Arrays.toString(sol.searchRange(nums2, 6))); // Expected: [-1, -1]

        // Test case 3
        int[] nums3 = {};
        System.out.println("Test 3: " + Arrays.toString(sol.searchRange(nums3, 0))); // Expected: [-1, -1]

        // Test case 4
        int[] nums4 = {1};
        System.out.println("Test 4: " + Arrays.toString(sol.searchRange(nums4, 1))); // Expected: [0, 0]

        // Test case 5
        int[] nums5 = {2, 2};
        System.out.println("Test 5: " + Arrays.toString(sol.searchRange(nums5, 2))); // Expected: [0, 1]
    }
}
