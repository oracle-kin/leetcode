package backtracking;

/**
 * 79. Word Search
 * Difficulty: Medium
 * Tags: Array, String, Backtracking, Matrix
 * URL: https://leetcode.com/problems/word-search/
 *
 * Problem Description:
 * Given an m x n grid of characters and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells
 * (horizontally or vertically). The same cell may not be used more than once.
 *
 * Complexity: O(M * N * 4^L) time where L = word length, O(L) recursion stack.
 */
public class Leetcode79 {

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        if (idx == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(idx)) return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found = dfs(board, i + 1, j, word, idx + 1)
                     || dfs(board, i - 1, j, word, idx + 1)
                     || dfs(board, i, j + 1, word, idx + 1)
                     || dfs(board, i, j - 1, word, idx + 1);

        board[i][j] = temp;
        return found;
    }

    public static void main(String[] args) {
        Leetcode79 sol = new Leetcode79();

        // Test 1: [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word="ABCCED" => true
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println("Test 1 (ABCCED): " + sol.exist(board, "ABCCED")); // true

        // Test 2: word="SEE" => true
        System.out.println("Test 2 (SEE): " + sol.exist(board, "SEE")); // true

        // Test 3: word="ABCB" => false
        System.out.println("Test 3 (ABCB): " + sol.exist(board, "ABCB")); // false
    }
}
