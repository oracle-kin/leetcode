package dp;

import java.util.*;

/**
 * 118. Pascal's Triangle
 * Difficulty: Easy
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/pascal's-triangle/
 *
 * Problem Description:
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 */
public class Leetcode118 {
    /**
     * DP: each row is built from the previous row.
     * row[j] = prevRow[j-1] + prevRow[j] for middle elements, 1 at edges.
     * Time: O(numRows^2), Space: O(numRows^2) for result
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode118 sol = new Leetcode118();
        System.out.println(sol.generate(5)); // Expected: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        System.out.println(sol.generate(1)); // Expected: [[1]]
        System.out.println(sol.generate(0)); // Expected: []
    }
}
