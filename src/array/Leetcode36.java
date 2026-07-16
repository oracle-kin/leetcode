package array;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. Valid Sudoku
 * Difficulty: Medium
 * Tags: Array, Hash Table, Matrix
 * URL: https://leetcode.com/problems/valid-sudoku/
 *
 * Problem Description:
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated
 * according to the rules: each row, each column, and each 3x3 sub-box must contain
 * the digits 1-9 without repetition.
 */
public class Leetcode36 {

    /**
     * Time: O(1)   — board is always 9x9 (81 cells)
     * Space: O(1)  — at most 9 entries per set
     */
    public boolean isValidSudoku(char[][] board) {
        // Use arrays of sets for rows, columns, and boxes
        @SuppressWarnings("unchecked")
        Set<Character>[] rows = new HashSet[9];
        @SuppressWarnings("unchecked")
        Set<Character>[] cols = new HashSet[9];
        @SuppressWarnings("unchecked")
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;

                int boxIdx = (r / 3) * 3 + (c / 3);
                if (rows[r].contains(ch) || cols[c].contains(ch) || boxes[boxIdx].contains(ch)) {
                    return false;
                }
                rows[r].add(ch);
                cols[c].add(ch);
                boxes[boxIdx].add(ch);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode36 sol = new Leetcode36();

        char[][] board1 = {
            { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
            { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
            { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
            { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
            { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
            { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
            { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
            { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
            { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println("Test 1: " + sol.isValidSudoku(board1) + " | Expected: true");

        char[][] board2 = {
            { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
            { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
            { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
            { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
            { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
            { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
            { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
            { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
            { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        System.out.println("Test 2: " + sol.isValidSudoku(board2) + " | Expected: false");
    }
}
