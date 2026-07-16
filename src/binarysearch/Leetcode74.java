package binarysearch;

/**
 * 74. Search a 2D Matrix
 * Difficulty: Medium
 * Tags: Array, Binary Search, Matrix
 * URL: https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Problem Description:
 * Search for a target value in an m x n matrix with the following properties:
 * - Integers in each row are sorted left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Complexity: O(log(m * n)) time, O(1) space
 * Algorithm: Treat the 2D matrix as a flattened 1D sorted array.
 * Map a 1D index to 2D coordinates: row = index / n, col = index % n.
 */
public class Leetcode74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = matrix[mid / n][mid % n];

            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Leetcode74 sol = new Leetcode74();

        // Test case 1
        int[][] matrix1 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println("Test 1: " + sol.searchMatrix(matrix1, 3)); // Expected: true

        // Test case 2
        System.out.println("Test 2: " + sol.searchMatrix(matrix1, 13)); // Expected: false

        // Test case 3
        int[][] matrix3 = {{1}};
        System.out.println("Test 3: " + sol.searchMatrix(matrix3, 1)); // Expected: true

        // Test case 4
        System.out.println("Test 4: " + sol.searchMatrix(matrix3, 2)); // Expected: false

        // Test case 5
        int[][] matrix5 = {{1, 1}};
        System.out.println("Test 5: " + sol.searchMatrix(matrix5, 2)); // Expected: false
    }
}
