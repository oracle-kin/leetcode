package binarysearch;

/**
 * 240. Search a 2D Matrix II
 * Difficulty: Medium
 * Tags: Array, Binary Search, Divide and Conquer, Matrix
 * URL: https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * Problem Description:
 * Search for a target value in an m x n matrix with sorted rows and sorted columns.
 * - Integers in each row are sorted in ascending from left to right.
 * - Integers in each column are sorted in ascending from top to bottom.
 *
 * Complexity: O(m + n) time, O(1) space
 * Algorithm: Start from the top-right corner. If current element > target, move left.
 * If current element < target, move down. This eliminates one row or column each step.
 */
public class Leetcode240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0, col = n - 1;

        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Leetcode240 sol = new Leetcode240();

        // Test case 1
        int[][] matrix1 = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        System.out.println("Test 1: " + sol.searchMatrix(matrix1, 5)); // Expected: true

        // Test case 2
        System.out.println("Test 2: " + sol.searchMatrix(matrix1, 20)); // Expected: false

        // Test case 3
        int[][] matrix3 = {{-5}};
        System.out.println("Test 3: " + sol.searchMatrix(matrix3, -5)); // Expected: true

        // Test case 4
        int[][] matrix4 = {{1, 1}};
        System.out.println("Test 4: " + sol.searchMatrix(matrix4, 2)); // Expected: false

        // Test case 5
        int[][] matrix5 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Test 5: " + sol.searchMatrix(matrix5, 6)); // Expected: true
    }
}
