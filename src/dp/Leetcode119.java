package dp;

import java.util.*;

/**
 * 119. Pascal's Triangle II
 * Difficulty: Easy
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/pascal's-triangle-ii/
 *
 * Problem Description:
 * Return the rowIndex-th (0-indexed) row of Pascal's triangle.
 */
public class Leetcode119 {
    /**
     * DP with O(k) space: build row in-place from right to left.
     * row[j] = row[j] + row[j-1] (using previous values).
     * Time: O(rowIndex^2), Space: O(rowIndex)
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            row.add(1);
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    public static void main(String[] args) {
        Leetcode119 sol = new Leetcode119();
        System.out.println(sol.getRow(3)); // Expected: [1, 3, 3, 1]
        System.out.println(sol.getRow(0)); // Expected: [1]
        System.out.println(sol.getRow(1)); // Expected: [1, 1]
        System.out.println(sol.getRow(4)); // Expected: [1, 4, 6, 4, 1]
    }
}
