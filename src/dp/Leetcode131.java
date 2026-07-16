package dp;

import java.util.*;

/**
 * 131. Palindrome Partitioning
 * Difficulty: Medium
 * Tags: String, Dynamic Programming, Backtracking
 * URL: https://leetcode.com/problems/palindrome-partitioning/
 *
 * Problem Description:
 * Partition a string such that every substring of the partition is a palindrome. Return all possible partitions.
 */
public class Leetcode131 {
    /**
     * Backtracking + DP palindrome check.
     * Precompute palindrome table with DP (O(n^2)), then DFS to collect partitions.
     * Time: O(n * 2^n) worst-case for result generation, O(n^2) for palindrome table.
     * Space: O(n^2) for table + O(n) recursion stack.
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, isPal, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, boolean[][] isPal, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPal[start][end]) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, isPal, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Leetcode131 sol = new Leetcode131();
        System.out.println(sol.partition("aab"));
        // Expected: [[a, a, b], [aa, b]]
        System.out.println(sol.partition("a"));
        // Expected: [[a]]
    }
}
