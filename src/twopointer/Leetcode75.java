package twopointer;

/**
 * 75. Sort Colors
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Sorting
 * URL: https://leetcode.com/problems/sort-colors/
 *
 * Problem Description:
 * Sort an array with objects colored red (0), white (1), blue (2) in-place (Dutch National Flag).
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode75 {

    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Leetcode75 sol = new Leetcode75();

        // Test case 1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sol.sortColors(nums1);
        System.out.print("Test 1: ");
        for (int v : nums1) System.out.print(v + " ");
        System.out.println();
        // Expected: 0 0 1 1 2 2

        // Test case 2
        int[] nums2 = {2, 0, 1};
        sol.sortColors(nums2);
        System.out.print("Test 2: ");
        for (int v : nums2) System.out.print(v + " ");
        System.out.println();
        // Expected: 0 1 2

        // Test case 3: already sorted
        int[] nums3 = {0, 1, 2};
        sol.sortColors(nums3);
        System.out.print("Test 3: ");
        for (int v : nums3) System.out.print(v + " ");
        System.out.println();
        // Expected: 0 1 2
    }
}
