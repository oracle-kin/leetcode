package stack;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * Difficulty: Easy
 * Tags: String, Stack
 * URL: https://leetcode.com/problems/valid-parentheses/
 *
 * Problem Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid. An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
public class Leetcode20 {

    /**
     * Time: O(n)  — single pass through the string
     * Space: O(n) — stack can hold up to n characters in worst case
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (ch == ')' && top != '(') return false;
                if (ch == '}' && top != '{') return false;
                if (ch == ']' && top != '[') return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Leetcode20 sol = new Leetcode20();

        System.out.println("Test 1: " + sol.isValid("()") + " | Expected: true");
        System.out.println("Test 2: " + sol.isValid("()[]{}") + " | Expected: true");
        System.out.println("Test 3: " + sol.isValid("(]") + " | Expected: false");
        System.out.println("Test 4: " + sol.isValid("([)]") + " | Expected: false");
        System.out.println("Test 5: " + sol.isValid("{[]}") + " | Expected: true");
    }
}
