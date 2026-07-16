package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * Difficulty: Medium
 * Tags: Array, Backtracking, Bit Manipulation
 * URL: https://leetcode.com/problems/subsets-ii/
 *
 * Problem Description:
 * Given an integer array that may contain duplicates, return all possible
 * subsets without duplicate subsets.
 *
 * Complexity: O(N * 2^N) time, O(N) recursion stack space.
 */
public class Leetcode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] nums, int start) {
        result.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            curr.add(nums[i]);
            backtrack(result, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode90 sol = new Leetcode90();

        // Test 1: [1,2,2] => [[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println("Test 1: " + sol.subsetsWithDup(new int[]{1,2,2}));

        // Test 2: [0] => [[],[0]]
        System.out.println("Test 2: " + sol.subsetsWithDup(new int[]{0}));
    }
}
