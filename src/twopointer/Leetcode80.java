package twopointer;

/**
 * 80. Remove Duplicates from Sorted Array II
 * Difficulty: Medium
 * Tags: Array, Two Pointers
 * URL: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * Problem Description:
 * Remove duplicates in-place such that each unique element appears at most twice.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode80 {

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;

        int slow = 2; // position to place next allowed element
        for (int fast = 2; fast < nums.length; fast++) {
            // Compare with element two positions behind slow
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        Leetcode80 sol = new Leetcode80();

        // Test case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = sol.removeDuplicates(nums1);
        System.out.print("Test 1 (k=" + k1 + "): ");
        for (int i = 0; i < k1; i++) System.out.print(nums1[i] + " ");
        System.out.println();
        // Expected: k=5, [1,1,2,2,3]

        // Test case 2
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int k2 = sol.removeDuplicates(nums2);
        System.out.print("Test 2 (k=" + k2 + "): ");
        for (int i = 0; i < k2; i++) System.out.print(nums2[i] + " ");
        System.out.println();
        // Expected: k=7, [0,0,1,1,2,3,3]
    }
}
