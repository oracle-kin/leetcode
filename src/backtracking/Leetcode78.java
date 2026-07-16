package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * Difficulty: Medium
 * Tags: Array, Backtracking, Bit Manipulation
 * URL: https://leetcode.com/problems/subsets/
 *
 * Problem Description:
 * Given an integer array of unique elements, return all possible subsets (the power set).
 *
 * Complexity: O(N * 2^N) time, O(N) recursion stack space.
 */
public class Leetcode78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int[] nums, int start) {
        result.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(result, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode78 sol = new Leetcode78();

        // Test 1: [1,2,3] => [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        System.out.println("Test 1: " + sol.subsets(new int[]{1,2,3}));

        // Test 2: [0] => [[],[0]]
        System.out.println("Test 2: " + sol.subsets(new int[]{0}));
    }
}
