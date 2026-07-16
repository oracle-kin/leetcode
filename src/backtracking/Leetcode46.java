package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 * Difficulty: Medium
 * Tags: Array, Backtracking
 * URL: https://leetcode.com/problems/permutations/
 *
 * Problem Description:
 * Given an array nums of distinct integers, return all possible permutations.
 *
 * Complexity: O(N * N!) time, O(N!) space for output, O(N) recursion stack.
 */
public class Leetcode46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] nums, boolean[] used) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack(result, curr, nums, used);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Leetcode46 sol = new Leetcode46();

        // Test 1: [1,2,3] => all permutations
        System.out.println("Test 1: " + sol.permute(new int[]{1,2,3}));

        // Test 2: [0,1] => [[0,1],[1,0]]
        System.out.println("Test 2: " + sol.permute(new int[]{0,1}));

        // Test 3: [1] => [[1]]
        System.out.println("Test 3: " + sol.permute(new int[]{1}));
    }
}
