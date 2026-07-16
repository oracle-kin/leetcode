package twopointer;

/**
 * 189. Rotate Array
 * Difficulty: Medium
 * Tags: Array, Math, Two Pointers
 * URL: https://leetcode.com/problems/rotate-array/
 *
 * Problem Description:
 * Rotate an array to the right by k steps.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode189 {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;

        // Reverse entire array
        reverse(nums, 0, n - 1);
        // Reverse first k elements
        reverse(nums, 0, k - 1);
        // Reverse remaining n-k elements
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Leetcode189 sol = new Leetcode189();

        // Test case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        sol.rotate(nums1, 3);
        System.out.print("Test 1: ");
        for (int v : nums1) System.out.print(v + " ");
        System.out.println();
        // Expected: 5 6 7 1 2 3 4

        // Test case 2
        int[] nums2 = {-1, -100, 3, 99};
        sol.rotate(nums2, 2);
        System.out.print("Test 2: ");
        for (int v : nums2) System.out.print(v + " ");
        System.out.println();
        // Expected: 3 99 -1 -100

        // Test case 3: k larger than n
        int[] nums3 = {1, 2};
        sol.rotate(nums3, 5);
        System.out.print("Test 3: ");
        for (int v : nums3) System.out.print(v + " ");
        System.out.println();
        // Expected: 2 1 (5 % 2 = 1 rotation)
    }
}
