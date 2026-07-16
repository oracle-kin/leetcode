package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Sorting
 * URL: https://leetcode.com/problems/3sum/
 *
 * Problem Description:
 * Find all unique triplets in the array which give the sum of zero.
 *
 * Time: O(n^2) | Space: O(1) (excluding output)
 */
public class Leetcode15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate i
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;  // skip duplicate left
                    while (left < right && nums[right] == nums[right - 1]) right--; // skip duplicate right
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode15 sol = new Leetcode15();

        // Test case 1: standard case
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test 1: " + sol.threeSum(nums1));
        // Expected: [[-1,-1,2],[-1,0,1]]

        // Test case 2: no triplets
        int[] nums2 = {0, 1, 1};
        System.out.println("Test 2: " + sol.threeSum(nums2));
        // Expected: []

        // Test case 3: all zeros
        int[] nums3 = {0, 0, 0};
        System.out.println("Test 3: " + sol.threeSum(nums3));
        // Expected: [[0,0,0]]
    }
}
