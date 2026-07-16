package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * Difficulty: Medium
 * Tags: Array, Matrix, Simulation
 * URL: https://leetcode.com/problems/spiral-matrix/
 *
 * Problem Description:
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class Leetcode54 {

    /**
     * Time: O(m * n) — each element visited once
     * Space: O(1)   — excluding the output list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse right
            for (int c = left; c <= right; c++) {
                result.add(matrix[top][c]);
            }
            top++;

            // Traverse down
            for (int r = top; r <= bottom; r++) {
                result.add(matrix[r][right]);
            }
            right--;

            // Traverse left (check if row still exists)
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    result.add(matrix[bottom][c]);
                }
                bottom--;
            }

            // Traverse up (check if column still exists)
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    result.add(matrix[r][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode54 sol = new Leetcode54();

        int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println("Test 1: " + sol.spiralOrder(matrix1) + " | Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]");

        int[][] matrix2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println("Test 2: " + sol.spiralOrder(matrix2) + " | Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]");

        int[][] matrix3 = { { 1 } };
        System.out.println("Test 3: " + sol.spiralOrder(matrix3) + " | Expected: [1]");
    }
}
