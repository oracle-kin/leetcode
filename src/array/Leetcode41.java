package array;

/**
 * 41. First Missing Positive
 * Difficulty: Hard
 * Tags: Array, Hash Table
 * URL: https://leetcode.com/problems/first-missing-positive/
 *
 * Problem Description:
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 */
public class Leetcode41 {

    /**
     * Time: O(n)  — each element is swapped at most once
     * Space: O(1) — in-place rearrangement
     *
     * Approach: Cyclic sort — place each number x (1 <= x <= n) at index x-1.
     * Then scan to find the first index where nums[i] != i+1.
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Place each positive number at its correct index
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // Find the first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Leetcode41 sol = new Leetcode41();

        System.out.println("Test 1: " + sol.firstMissingPositive(new int[] { 1, 2, 0 }) + " | Expected: 3");
        System.out.println("Test 2: " + sol.firstMissingPositive(new int[] { 3, 4, -1, 1 }) + " | Expected: 2");
        System.out.println("Test 3: " + sol.firstMissingPositive(new int[] { 7, 8, 9, 11, 12 }) + " | Expected: 1");
        System.out.println("Test 4: " + sol.firstMissingPositive(new int[] { 1 }) + " | Expected: 2");
    }
}
