package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Difficulty: Medium
 * Tags: String, Dynamic Programming, Backtracking
 * URL: https://leetcode.com/problems/generate-parentheses/
 *
 * Problem Description:
 * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 */
public class Leetcode22 {

    /**
     * Backtracking / Catalan DP | O(4^n / sqrt(n)) time, O(n) recursion stack
     * Use backtracking: track open/close counts. Add '(' if open < n, ')' if close < open.
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            result.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(result, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(result, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode22 sol = new Leetcode22();
        System.out.println(sol.generateParenthesis(3)); // [((())), (()()), (())(), ()(()), ()()()]
        System.out.println(sol.generateParenthesis(1)); // [()]
        System.out.println(sol.generateParenthesis(2)); // [(())), ()()]
    }
}
