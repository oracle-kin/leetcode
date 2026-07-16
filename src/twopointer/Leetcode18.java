package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Sorting
 * URL: https://leetcode.com/problems/4sum/
 *
 * Problem Description:
 * Given an array nums of n integers, return all unique quadruplets that sum to target.
 *
 * Time: O(n^3) | Space: O(1) (excluding output)
 */
public class Leetcode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate i
            // Pruning: smallest possible sum from i > target, break early
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            // Pruning: largest possible sum from i < target, skip this i
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // skip duplicate j
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode18 sol = new Leetcode18();

        // Test case 1
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        System.out.println("Test 1: " + sol.fourSum(nums1, 0));
        // Expected: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        // Test case 2
        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println("Test 2: " + sol.fourSum(nums2, 8));
        // Expected: [[2,2,2,2]]

        // Test case 3
        int[] nums3 = {1000000000, 1000000000, 1000000000, 1000000000};
        System.out.println("Test 3: " + sol.fourSum(nums3, -294967296));
        // Expected: []
    }
}
