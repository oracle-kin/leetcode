package dp;

import java.util.*;

/**
 * 120. Triangle
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/triangle/
 *
 * Problem Description:
 * Find the minimum path sum from top to bottom in a triangle array.
 */
public class Leetcode120 {
    /**
     * Bottom-up DP: start from second-to-last row, update each cell with
     * triangle[r][c] += min(triangle[r+1][c], triangle[r+1][c+1]).
     * Time: O(n^2), Space: O(1) in-place (modifies input)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int r = triangle.size() - 2; r >= 0; r--) {
            for (int c = 0; c < triangle.get(r).size(); c++) {
                int minBelow = Math.min(triangle.get(r + 1).get(c), triangle.get(r + 1).get(c + 1));
                triangle.get(r).set(c, triangle.get(r).get(c) + minBelow);
            }
        }
        return triangle.get(0).get(0);
    }

    public static void main(String[] args) {
        Leetcode120 sol = new Leetcode120();
        List<List<Integer>> t1 = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        System.out.println(sol.minimumTotal(t1)); // Expected: 11 (2->3->5->1)
        List<List<Integer>> t2 = Arrays.asList(Arrays.asList(-10));
        System.out.println(sol.minimumTotal(t2)); // Expected: -10
    }
}
