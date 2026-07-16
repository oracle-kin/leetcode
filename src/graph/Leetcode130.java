package graph;

import java.util.Arrays;

/**
 * 130. Surrounded Regions
 * Difficulty: Medium
 * Tags: Array, DFS, BFS, Union Find, Matrix
 * URL: https://leetcode.com/problems/surrounded-regions/
 *
 * Problem Description:
 * Capture all regions that are 4-directionally surrounded by 'X' on a board.
 */
public class Leetcode130 {

    /**
     * Time: O(m * n)  — each cell visited at most twice
     * Space: O(m * n) — recursion stack in worst case
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        // Mark border-connected 'O' cells with '#'
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        // Flip remaining 'O' to 'X', and '#' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = '#';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    public static void main(String[] args) {
        Leetcode130 sol = new Leetcode130();

        // Test 1
        char[][] board1 = {
            { 'X', 'X', 'X', 'X' },
            { 'X', 'O', 'O', 'X' },
            { 'X', 'X', 'O', 'X' },
            { 'X', 'O', 'X', 'X' }
        };
        sol.solve(board1);
        System.out.println("Test 1:");
        for (char[] row : board1) System.out.println(Arrays.toString(row));
        System.out.println("Expected: O in middle should be flipped");

        // Test 2
        char[][] board2 = { { 'X' } };
        sol.solve(board2);
        System.out.println("Test 2: " + Arrays.deepToString(board2) + " | Expected: [[X]]");
    }
}
