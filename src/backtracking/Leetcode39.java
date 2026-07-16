package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. Combination Sum
 * Difficulty: Medium
 * Tags: Array, Backtracking
 * URL: https://leetcode.com/problems/combination-sum/
 *
 * Problem Description:
 * Find all unique combinations of candidates where the candidate numbers sum to target.
 * The same number may be chosen from candidates an unlimited number of times.
 *
 * Complexity: O(N^(T/M)) time where T = target, M = minimal candidate value.
 * O(T/M) recursion stack space.
 */
public class Leetcode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
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
            curr.add(candidates[i]);
            backtrack(result, curr, candidates, remain - candidates[i], i);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode39 sol = new Leetcode39();

        // Test 1: [2,3,6,7], target=7 => [[2,2,3],[7]]
        System.out.println("Test 1: " + sol.combinationSum(new int[]{2,3,6,7}, 7));

        // Test 2: [2,3,5], target=8 => [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println("Test 2: " + sol.combinationSum(new int[]{2,3,5}, 8));

        // Test 3: [2], target=1 => []
        System.out.println("Test 3: " + sol.combinationSum(new int[]{2}, 1));
    }
}
