package array;

import java.util.Arrays;

/**
 * 73. Set Matrix Zeroes
 * Difficulty: Medium
 * Tags: Array, Hash Table, Matrix
 * URL: https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Problem Description:
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in-place.
 */
public class Leetcode73 {

    /**
     * Time: O(m * n) — traverse matrix twice
     * Space: O(1)   — use first row and first column as markers
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if first row has any zero
        for (int c = 0; c < n; c++) {
            if (matrix[0][c] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column has any zero
        for (int r = 0; r < m; r++) {
            if (matrix[r][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row and column as markers for the rest of the matrix
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        // Zero out cells based on markers
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        // Zero out first row if needed
        if (firstRowZero) {
            for (int c = 0; c < n; c++) {
                matrix[0][c] = 0;
            }
        }

        // Zero out first column if needed
        if (firstColZero) {
            for (int r = 0; r < m; r++) {
                matrix[r][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Leetcode73 sol = new Leetcode73();

        int[][] matrix1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        sol.setZeroes(matrix1);
        System.out.println("Test 1: " + Arrays.deepToString(matrix1)
                + " | Expected: [[1,0,1],[0,0,0],[1,0,1]]");

        int[][] matrix2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        sol.setZeroes(matrix2);
        System.out.println("Test 2: " + Arrays.deepToString(matrix2)
                + " | Expected: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]");
    }
}
