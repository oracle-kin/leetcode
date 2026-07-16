package binarysearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Problem Description:
 * Find the minimum element in a rotated sorted array with unique values.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Binary search. Compare nums[mid] with nums[right].
 * If nums[mid] > nums[right], the minimum is in the right half.
 * Otherwise, the minimum is in the left half (including mid).
 */
public class Leetcode153 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in the right half
                left = mid + 1;
            } else {
                // Minimum is in the left half (including mid)
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        Leetcode153 sol = new Leetcode153();

        // Test case 1
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1: " + sol.findMin(nums1)); // Expected: 1

        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2: " + sol.findMin(nums2)); // Expected: 0

        // Test case 3
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test 3: " + sol.findMin(nums3)); // Expected: 11

        // Test case 4
        int[] nums4 = {2, 1};
        System.out.println("Test 4: " + sol.findMin(nums4)); // Expected: 1

        // Test case 5
        int[] nums5 = {1};
        System.out.println("Test 5: " + sol.findMin(nums5)); // Expected: 1
    }
}
