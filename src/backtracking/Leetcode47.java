package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 * Difficulty: Medium
 * Tags: Array, Backtracking
 * URL: https://leetcode.com/problems/permutations-ii/
 *
 * Problem Description:
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Complexity: O(N * N!) time, O(N) recursion stack, O(N) used array.
 */
public class Leetcode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            curr.add(nums[i]);
            backtrack(result, curr, nums, used);
            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Leetcode47 sol = new Leetcode47();

        // Test 1: [1,1,2] => [[1,1,2],[1,2,1],[2,1,1]]
        System.out.println("Test 1: " + sol.permuteUnique(new int[]{1,1,2}));

        // Test 2: [1,2,3] => all unique permutations
        System.out.println("Test 2: " + sol.permuteUnique(new int[]{1,2,3}));
    }
}
