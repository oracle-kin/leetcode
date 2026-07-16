package backtracking;

/**
 * 52. N-Queens II
 * Difficulty: Hard
 * Tags: Backtracking
 * URL: https://leetcode.com/problems/n-queens-ii/
 *
 * Problem Description:
 * Return the number of distinct solutions to the n-queens puzzle.
 *
 * Complexity: O(N!) time, O(N) recursion stack space.
 */
public class Leetcode52 {

    private int count = 0;

    public int totalNQueens(int n) {
        count = 0;
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row - col + n - 1
        boolean[] diag2 = new boolean[2 * n - 1]; // row + col
        backtrack(0, n, cols, diag1, diag2);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (cols[col] || diag1[d1] || diag2[d2]) continue;
            cols[col] = diag1[d1] = diag2[d2] = true;
            backtrack(row + 1, n, cols, diag1, diag2);
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    public static void main(String[] args) {
        Leetcode52 sol = new Leetcode52();

        // Test 1: n=4 => 2
        System.out.println("Test 1 (n=4): " + sol.totalNQueens(4)); // 2

        // Test 2: n=1 => 1
        System.out.println("Test 2 (n=1): " + sol.totalNQueens(1)); // 1

        // Test 3: n=8 => 92
        System.out.println("Test 3 (n=8): " + sol.totalNQueens(8)); // 92
    }
}
