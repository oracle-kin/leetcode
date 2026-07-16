package math;

import java.util.Arrays;

/**
 * 48. Rotate Image
 * Difficulty: Medium
 * Tags: Array, Math, Matrix
 * URL: https://leetcode.com/problems/rotate-image/
 *
 * Problem Description:
 * Rotate an n x n 2D matrix by 90 degrees clockwise in-place.
 *
 * Complexity: O(n^2) time, O(1) space
 * Algorithm: Two-step approach:
 * 1. Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
 * 2. Reverse each row (swap matrix[i][j] with matrix[i][n-1-j]).
 */
public class Leetcode48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose (swap across the main diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Leetcode48 sol = new Leetcode48();

        // Test case 1
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        sol.rotate(matrix1);
        System.out.println("Test 1: " + Arrays.deepToString(matrix1));
        // Expected: [[7,4,1],[8,5,2],[9,6,3]]

        // Test case 2
        int[][] matrix2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        sol.rotate(matrix2);
        System.out.println("Test 2: " + Arrays.deepToString(matrix2));
        // Expected: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

        // Test case 3
        int[][] matrix3 = {{1}};
        sol.rotate(matrix3);
        System.out.println("Test 3: " + Arrays.deepToString(matrix3)); // Expected: [[1]]

        // Test case 4
        int[][] matrix4 = {{1, 2}, {3, 4}};
        sol.rotate(matrix4);
        System.out.println("Test 4: " + Arrays.deepToString(matrix4)); // Expected: [[3,1],[4,2]]
    }
}
