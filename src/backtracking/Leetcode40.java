package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Difficulty: Medium
 * Tags: Array, Backtracking
 * URL: https://leetcode.com/problems/combination-sum-ii/
 *
 * Problem Description:
 * Find all unique combinations of candidates where each number may only be used once.
 *
 * Complexity: O(2^N) worst case time. O(N) recursion stack space.
 */
public class Leetcode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] candidates, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            curr.add(candidates[i]);
            backtrack(result, curr, candidates, remain - candidates[i], i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode40 sol = new Leetcode40();

        // Test 1: [10,1,2,7,6,1,5], target=8 => [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println("Test 1: " + sol.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));

        // Test 2: [2,5,2,1,2], target=5 => [[1,2,2],[5]]
        System.out.println("Test 2: " + sol.combinationSum2(new int[]{2,5,2,1,2}, 5));
    }
}
