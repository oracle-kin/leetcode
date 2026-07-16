package stack;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Difficulty: Hard
 * Tags: Math, String, Stack, Recursion
 * URL: https://leetcode.com/problems/basic-calculator/
 *
 * Problem Description:
 * Given a string s representing a valid expression, implement a basic calculator
 * to evaluate it. The expression may contain digits, '+', '-', '(', ')', and ' '.
 */
public class Leetcode224 {

    /**
     * Time: O(n)  — single pass through the string
     * Space: O(n) — stack stores signs and results for parentheses
     *
     * Approach: Track current result, current sign, and use a stack to
     * save/resume state when encountering parentheses.
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;   // 1 = positive, -1 = negative
        int num = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } else if (ch == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } else if (ch == '(') {
                // Save current result and sign
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // Finalize current number
                result += sign * num;
                num = 0;
                // Pop sign and previous result
                result *= stack.pop();   // sign before '('
                result += stack.pop();   // result before '('
            }
            // Skip spaces
        }

        result += sign * num;
        return result;
    }

    public static void main(String[] args) {
        Leetcode224 sol = new Leetcode224();

        System.out.println("Test 1: " + sol.calculate("1 + 1") + " | Expected: 2");
        System.out.println("Test 2: " + sol.calculate(" 2-1 + 2 ") + " | Expected: 3");
        System.out.println("Test 3: " + sol.calculate("(1+(4+5+2)-3)+(6+8)") + " | Expected: 23");
        System.out.println("Test 4: " + sol.calculate("-2+ 1") + " | Expected: -1");
        System.out.println("Test 5: " + sol.calculate("-(2+3)") + " | Expected: -5");
    }
}
