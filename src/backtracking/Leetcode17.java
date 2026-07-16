package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number
 * Difficulty: Medium
 * Tags: Hash Table, String, Backtracking
 * URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Problem Description:
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 *
 * Complexity: O(4^N) time where N is length of digits, O(N) recursion stack space
 */
public class Leetcode17 {

    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, String digits, int idx) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(result, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Leetcode17 sol = new Leetcode17();

        // Test 1: "23" => ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println("Test 1: " + sol.letterCombinations("23"));

        // Test 2: "" => []
        System.out.println("Test 2: " + sol.letterCombinations(""));

        // Test 3: "2" => ["a","b","c"]
        System.out.println("Test 3: " + sol.letterCombinations("2"));
    }
}
