package graph;

/**
 * 200. Number of Islands
 * Difficulty: Medium
 * Tags: Array, DFS, BFS, Union Find, Matrix
 * URL: https://leetcode.com/problems/number-of-islands/
 *
 * Problem Description:
 * Count the number of islands in a 2D grid. An island is surrounded by water (1=land, 0=water).
 */
public class Leetcode200 {

    /**
     * Time: O(m * n)  — each cell visited once
     * Space: O(m * n) — recursion stack in worst case (all land)
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0'; // mark as visited by sinking
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        Leetcode200 sol = new Leetcode200();

        // Test 1
        char[][] grid1 = {
            { '1', '1', '1', '1', '0' },
            { '1', '1', '0', '1', '0' },
            { '1', '1', '0', '0', '0' },
            { '0', '0', '0', '0', '0' }
        };
        System.out.println("Test 1: " + sol.numIslands(grid1) + " | Expected: 1");

        // Test 2
        char[][] grid2 = {
            { '1', '1', '0', '0', '0' },
            { '1', '1', '0', '0', '0' },
            { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' }
        };
        System.out.println("Test 2: " + sol.numIslands(grid2) + " | Expected: 3");

        // Test 3
        System.out.println("Test 3: " + sol.numIslands(new char[][] {}) + " | Expected: 0");
    }
}
