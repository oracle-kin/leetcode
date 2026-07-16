package twopointer;

/**
 * 88. Merge Sorted Array
 * Difficulty: Easy
 * Tags: Array, Two Pointers, Sorting
 * URL: https://leetcode.com/problems/merge-sorted-array/
 *
 * Problem Description:
 * Merge nums2 into nums1 as one sorted array in-place.
 * nums1 has length m + n where first m elements are sorted and last n are 0s to be overwritten.
 *
 * Time: O(m + n) | Space: O(1)
 */
public class Leetcode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;     // pointer for nums1 (actual elements)
        int p2 = n - 1;     // pointer for nums2
        int p = m + n - 1;  // pointer for merged position

        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }

    public static void main(String[] args) {
        Leetcode88 sol = new Leetcode88();

        // Test case 1
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        sol.merge(nums1, 3, nums2, 3);
        System.out.print("Test 1: ");
        for (int v : nums1) System.out.print(v + " ");
        System.out.println();
        // Expected: 1 2 2 3 5 6

        // Test case 2
        int[] nums1b = {1};
        int[] nums2b = {};
        sol.merge(nums1b, 1, nums2b, 0);
        System.out.print("Test 2: ");
        for (int v : nums1b) System.out.print(v + " ");
        System.out.println();
        // Expected: 1

        // Test case 3
        int[] nums1c = {0};
        int[] nums2c = {1};
        sol.merge(nums1c, 0, nums2c, 1);
        System.out.print("Test 3: ");
        for (int v : nums1c) System.out.print(v + " ");
        System.out.println();
        // Expected: 1
    }
}
