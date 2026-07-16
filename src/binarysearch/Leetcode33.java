package binarysearch;

/**
 * 33. Search in Rotated Sorted Array
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Problem Description:
 * Search for a target value in a rotated sorted array with distinct values.
 * Return the index of target, or -1 if not found.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Binary search. At each step, determine which half is sorted,
 * then check if the target lies within the sorted half to decide which
 * direction to search.
 */
public class Leetcode33 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Leetcode33 sol = new Leetcode33();

        // Test case 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 1: " + sol.search(nums1, 0)); // Expected: 4

        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2: " + sol.search(nums2, 3)); // Expected: -1

        // Test case 3
        int[] nums3 = {1};
        System.out.println("Test 3: " + sol.search(nums3, 0)); // Expected: -1

        // Test case 4
        int[] nums4 = {1, 3};
        System.out.println("Test 4: " + sol.search(nums4, 3)); // Expected: 1

        // Test case 5
        int[] nums5 = {5, 1, 3};
        System.out.println("Test 5: " + sol.search(nums5, 5)); // Expected: 0
    }
}
