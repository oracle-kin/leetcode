package twopointer;

/**
 * 31. Next Permutation
 * Difficulty: Medium
 * Tags: Array, Two Pointers
 * URL: https://leetcode.com/problems/next-permutation/
 *
 * Problem Description:
 * Rearrange numbers into the lexicographically next greater permutation.
 * If not possible, rearrange into the lowest possible order (sorted ascending).
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode31 {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // Step 1: find first decreasing element from right
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // Step 2: find element just larger than nums[i] from right
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: reverse the suffix from i+1 to end
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Leetcode31 sol = new Leetcode31();

        // Test case 1
        int[] nums1 = {1, 2, 3};
        sol.nextPermutation(nums1);
        System.out.print("Test 1: ");
        for (int v : nums1) System.out.print(v + " ");
        System.out.println();
        // Expected: [1,3,2]

        // Test case 2
        int[] nums2 = {3, 2, 1};
        sol.nextPermutation(nums2);
        System.out.print("Test 2: ");
        for (int v : nums2) System.out.print(v + " ");
        System.out.println();
        // Expected: [1,2,3]

        // Test case 3
        int[] nums3 = {1, 1, 5};
        sol.nextPermutation(nums3);
        System.out.print("Test 3: ");
        for (int v : nums3) System.out.print(v + " ");
        System.out.println();
        // Expected: [1,5,1]
    }
}
