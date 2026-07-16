package twopointer;

/**
 * 27. Remove Element
 * Difficulty: Easy
 * Tags: Array, Two Pointers
 * URL: https://leetcode.com/problems/remove-element/
 *
 * Problem Description:
 * Remove all instances of a given value in-place and return the new length.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode27 {

    public int removeElement(int[] nums, int val) {
        int slow = 0; // position to place next non-val element
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        Leetcode27 sol = new Leetcode27();

        // Test case 1
        int[] nums1 = {3, 2, 2, 3};
        int k1 = sol.removeElement(nums1, 3);
        System.out.print("Test 1 (k=" + k1 + "): ");
        for (int i = 0; i < k1; i++) System.out.print(nums1[i] + " ");
        System.out.println();
        // Expected: k=2, [2,2]

        // Test case 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = sol.removeElement(nums2, 2);
        System.out.print("Test 2 (k=" + k2 + "): ");
        for (int i = 0; i < k2; i++) System.out.print(nums2[i] + " ");
        System.out.println();
        // Expected: k=5, [0,1,3,0,4]
    }
}
