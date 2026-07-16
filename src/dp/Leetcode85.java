package dp;

/**
 * 85. Maximal Rectangle
 * Difficulty: Hard
 * Tags: Array, Dynamic Programming, Stack, Matrix, Monotonic Stack
 * URL: https://leetcode.com/problems/maximal-rectangle/
 *
 * Problem Description:
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's.
 */
public class Leetcode85 {
    /**
     * DP + Histogram | O(m*n) time, O(n) space
     * For each row, compute the histogram of consecutive 1's above.
     * Then compute the largest rectangle in that histogram using a monotonic stack.
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1, maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (top >= 0 && h < heights[stack[top]]) {
                int height = heights[stack[top--]];
                int width = (top == -1) ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack[++top] = i;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Leetcode85 sol = new Leetcode85();
        char[][] matrix1 = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(sol.maximalRectangle(matrix1)); // 6
        char[][] matrix2 = {{'0'}};
        System.out.println(sol.maximalRectangle(matrix2)); // 0
        char[][] matrix3 = {{'1'}};
        System.out.println(sol.maximalRectangle(matrix3)); // 1
    }
}
