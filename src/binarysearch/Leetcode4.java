package binarysearch;

/**
 * 4. Median of Two Sorted Arrays
 * Difficulty: Hard
 * Tags: Array, Binary Search, Divide and Conquer
 * URL: https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Problem Description:
 * Given two sorted arrays nums1 and nums2 of size m and n, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log(m+n)).
 *
 * Complexity: O(log(min(m, n))) time, O(1) space
 * Algorithm: Binary search on the smaller array to find the correct partition point.
 * The partition divides both arrays into left and right halves such that all elements
 * on the left are <= all elements on the right. Then the median is derived from the
 * boundary elements.
 */
public class Leetcode4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for O(log(min(m,n)))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;

        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = totalLeft - partition1;

            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Found the correct partition
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        Leetcode4 sol = new Leetcode4();

        // Test case 1
        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println("Test 1: " + sol.findMedianSortedArrays(nums1_1, nums2_1)); // Expected: 2.0

        // Test case 2
        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("Test 2: " + sol.findMedianSortedArrays(nums1_2, nums2_2)); // Expected: 2.5

        // Test case 3
        int[] nums1_3 = {0, 0};
        int[] nums2_3 = {0, 0};
        System.out.println("Test 3: " + sol.findMedianSortedArrays(nums1_3, nums2_3)); // Expected: 0.0

        // Test case 4
        int[] nums1_4 = {};
        int[] nums2_4 = {1};
        System.out.println("Test 4: " + sol.findMedianSortedArrays(nums1_4, nums2_4)); // Expected: 1.0

        // Test case 5
        int[] nums1_5 = {2};
        int[] nums2_5 = {};
        System.out.println("Test 5: " + sol.findMedianSortedArrays(nums1_5, nums2_5)); // Expected: 2.0
    }
}
