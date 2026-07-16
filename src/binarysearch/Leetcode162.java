package binarysearch;

/**
 * 162. Find Peak Element
 * Difficulty: Medium
 * Tags: Array, Binary Search
 * URL: https://leetcode.com/problems/find-peak-element/
 *
 * Problem Description:
 * Find a peak element (an element strictly greater than its neighbors) and return its index.
 * The array may contain multiple peaks; return the index of any one.
 * Assume nums[-1] = nums[n] = -infinity.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Binary search. Compare nums[mid] with nums[mid+1].
 * If nums[mid] < nums[mid+1], a peak must exist on the right side (ascending slope).
 * Otherwise, a peak exists on the left side or at mid itself.
 */
public class Leetcode162 {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Ascending slope, peak is on the right
                left = mid + 1;
            } else {
                // Descending slope, peak is on the left or at mid
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Leetcode162 sol = new Leetcode162();

        // Test case 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1: " + sol.findPeakElement(nums1)); // Expected: 2

        // Test case 2
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Test 2: " + sol.findPeakElement(nums2)); // Expected: 5 (or 1)

        // Test case 3
        int[] nums3 = {1};
        System.out.println("Test 3: " + sol.findPeakElement(nums3)); // Expected: 0

        // Test case 4
        int[] nums4 = {1, 2};
        System.out.println("Test 4: " + sol.findPeakElement(nums4)); // Expected: 1

        // Test case 5
        int[] nums5 = {2, 1};
        System.out.println("Test 5: " + sol.findPeakElement(nums5)); // Expected: 0
    }
}
