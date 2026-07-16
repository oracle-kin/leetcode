package binarysearch;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * Difficulty: Hard
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Problem Description:
 * Find the minimum element in a rotated sorted array that may contain duplicates.
 *
 * Complexity: O(log n) average, O(n) worst-case time; O(1) space
 * Algorithm: Binary search with duplicate handling. Compare nums[mid] with nums[right].
 * If nums[mid] > nums[right], minimum is in right half.
 * If nums[mid] < nums[right], minimum is in left half (including mid).
 * If nums[mid] == nums[right], we cannot determine which side, so decrement right.
 */
public class Leetcode154 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // nums[mid] == nums[right], cannot determine
                right--;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        Leetcode154 sol = new Leetcode154();

        // Test case 1
        int[] nums1 = {1, 3, 5};
        System.out.println("Test 1: " + sol.findMin(nums1)); // Expected: 1

        // Test case 2
        int[] nums2 = {2, 2, 2, 0, 1};
        System.out.println("Test 2: " + sol.findMin(nums2)); // Expected: 0

        // Test case 3
        int[] nums3 = {3, 3, 1, 3};
        System.out.println("Test 3: " + sol.findMin(nums3)); // Expected: 1

        // Test case 4
        int[] nums4 = {1, 1, 1};
        System.out.println("Test 4: " + sol.findMin(nums4)); // Expected: 1

        // Test case 5
        int[] nums5 = {10, 1, 10, 10, 10};
        System.out.println("Test 5: " + sol.findMin(nums5)); // Expected: 1
    }
}
