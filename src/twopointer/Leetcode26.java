package twopointer;

/**
 * 26. Remove Duplicates from Sorted Array
 * Difficulty: Easy
 * Tags: Array, Two Pointers
 * URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Problem Description:
 * Remove duplicates from a sorted array in-place and return the new length.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode26 {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 0; // position of last unique element
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        Leetcode26 sol = new Leetcode26();

        // Test case 1
        int[] nums1 = {1, 1, 2};
        int k1 = sol.removeDuplicates(nums1);
        System.out.print("Test 1 (k=" + k1 + "): ");
        for (int i = 0; i < k1; i++) System.out.print(nums1[i] + " ");
        System.out.println();
        // Expected: k=2, [1,2]

        // Test case 2
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = sol.removeDuplicates(nums2);
        System.out.print("Test 2 (k=" + k2 + "): ");
        for (int i = 0; i < k2; i++) System.out.print(nums2[i] + " ");
        System.out.println();
        // Expected: k=5, [0,1,2,3,4]
    }
}
