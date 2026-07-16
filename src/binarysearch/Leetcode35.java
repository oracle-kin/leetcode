package binarysearch;

/**
 * 35. Search Insert Position
 * Difficulty: Easy
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/search-insert-position/
 *
 * Problem Description:
 * Return the index if the target is found. If not, return the index where it would be inserted in order.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Standard binary search. When the target is not found, the 'left' pointer
 * naturally ends up at the insertion position.
 */
public class Leetcode35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Leetcode35 sol = new Leetcode35();

        // Test case 1
        int[] nums1 = {1, 3, 5, 6};
        System.out.println("Test 1: " + sol.searchInsert(nums1, 5)); // Expected: 2

        // Test case 2
        int[] nums2 = {1, 3, 5, 6};
        System.out.println("Test 2: " + sol.searchInsert(nums2, 2)); // Expected: 1

        // Test case 3
        int[] nums3 = {1, 3, 5, 6};
        System.out.println("Test 3: " + sol.searchInsert(nums3, 7)); // Expected: 4

        // Test case 4
        int[] nums4 = {1, 3, 5, 6};
        System.out.println("Test 4: " + sol.searchInsert(nums4, 0)); // Expected: 0

        // Test case 5
        int[] nums5 = {1};
        System.out.println("Test 5: " + sol.searchInsert(nums5, 0)); // Expected: 0
    }
}
