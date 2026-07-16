package binarysearch;

/**
 * 81. Search in Rotated Sorted Array II
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Problem Description:
 * Search for a target value in a rotated sorted array that may contain duplicates.
 * Return true if target exists, false otherwise.
 *
 * Complexity: O(log n) average, O(n) worst-case time; O(1) space
 * Algorithm: Binary search with duplicate handling. When nums[left] == nums[mid] == nums[right],
 * we cannot determine which half is sorted, so we shrink the search space by moving left++ and right--.
 * Otherwise, proceed like the standard rotated sorted array search.
 */
public class Leetcode81 {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // Handle duplicates: cannot determine sorted half
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                // Left half is sorted
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

        return false;
    }

    public static void main(String[] args) {
        Leetcode81 sol = new Leetcode81();

        // Test case 1
        int[] nums1 = {2, 5, 6, 0, 0, 1, 2};
        System.out.println("Test 1: " + sol.search(nums1, 0)); // Expected: true

        // Test case 2
        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        System.out.println("Test 2: " + sol.search(nums2, 3)); // Expected: false

        // Test case 3
        int[] nums3 = {1, 0, 1, 1, 1};
        System.out.println("Test 3: " + sol.search(nums3, 0)); // Expected: true

        // Test case 4
        int[] nums4 = {1, 1, 1, 1, 1};
        System.out.println("Test 4: " + sol.search(nums4, 2)); // Expected: false

        // Test case 5
        int[] nums5 = {3, 1};
        System.out.println("Test 5: " + sol.search(nums5, 1)); // Expected: true
    }
}
