package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 * Difficulty: Hard
 * Tags: Array, Backtracking
 * URL: https://leetcode.com/problems/n-queens/
 *
 * Problem Description:
 * Place n queens on an nxn chessboard such that no two queens attack each other.
 * Return all distinct solutions.
 *
 * Complexity: O(N!) time, O(N^2) space for output.
 */
public class Leetcode51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(result, board, 0, n);
        return result;
    }

    private void backtrack(List<List<String>> result, char[][] board, int row, int n) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) solution.add(new String(r));
            result.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrack(result, board, row + 1, n);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode51 sol = new Leetcode51();

        // Test 1: n=4 => 2 solutions
        List<List<String>> res = sol.solveNQueens(4);
        System.out.println("Test 1 (n=4): " + res.size() + " solutions");
        for (List<String> solution : res) {
            for (String row : solution) System.out.println(row);
            System.out.println();
        }
    }
}
