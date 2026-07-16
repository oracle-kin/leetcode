package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * Difficulty: Medium
 * Tags: Backtracking
 * URL: https://leetcode.com/problems/combinations/
 *
 * Problem Description:
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * Complexity: O(C(n,k) * k) time, O(k) recursion stack space.
 */
public class Leetcode77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curr, int n, int k, int start) {
        if (curr.size() == k) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {
            curr.add(i);
            backtrack(result, curr, n, k, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode77 sol = new Leetcode77();

        // Test 1: n=4, k=2 => [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
        System.out.println("Test 1: " + sol.combine(4, 2));

        // Test 2: n=1, k=1 => [[1]]
        System.out.println("Test 2: " + sol.combine(1, 1));
    }
}
