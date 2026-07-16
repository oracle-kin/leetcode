package array;

import java.util.Arrays;

/**
 * 59. Spiral Matrix II
 * Difficulty: Medium
 * Tags: Array, Matrix, Simulation
 * URL: https://leetcode.com/problems/spiral-matrix-ii/
 *
 * Problem Description:
 * Given a positive integer n, generate an n x n matrix filled with elements
 * from 1 to n^2 in spiral order.
 */
public class Leetcode59 {

    /**
     * Time: O(n²) — fill n² cells
     * Space: O(1) — excluding the output matrix
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {
            // Fill right
            for (int c = left; c <= right; c++) {
                matrix[top][c] = num++;
            }
            top++;

            // Fill down
            for (int r = top; r <= bottom; r++) {
                matrix[r][right] = num++;
            }
            right--;

            // Fill left
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    matrix[bottom][c] = num++;
                }
                bottom--;
            }

            // Fill up
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    matrix[r][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Leetcode59 sol = new Leetcode59();

        int[][] res1 = sol.generateMatrix(3);
        System.out.println("Test 1 (n=3):");
        for (int[] row : res1) {
            System.out.println("  " + Arrays.toString(row));
        }
        System.out.println("Expected: [[1,2,3],[8,9,4],[7,6,5]]");

        int[][] res2 = sol.generateMatrix(1);
        System.out.println("Test 2 (n=1): " + Arrays.deepToString(res2) + " | Expected: [[1]]");
    }
}
