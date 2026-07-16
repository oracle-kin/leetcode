package stack;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * Difficulty: Medium
 * Tags: Math, String, Stack
 * URL: https://leetcode.com/problems/basic-calculator-ii/
 *
 * Problem Description:
 * Given a string s which represents an expression, evaluate this expression and
 * return its value. The integer division should truncate toward zero. The expression
 * contains integers and operators ('+', '-', '*', '/') separated by spaces. No parentheses.
 */
public class Leetcode227 {

    /**
     * Time: O(n)  — single pass through the string
     * Space: O(n) — stack stores intermediate results
     *
     * Approach: Use a stack. For '+'/'-', push the number (with sign).
     * For '*'/'/', pop, compute, and push the result. Finally, sum the stack.
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';  // previous operator, default '+'

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                sign = ch;
                num = 0;
            }
        }

        int result = 0;
        for (int val : stack) {
            result += val;
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode227 sol = new Leetcode227();

        System.out.println("Test 1: " + sol.calculate("3+2*2") + " | Expected: 7");
        System.out.println("Test 2: " + sol.calculate(" 3/2 ") + " | Expected: 1");
        System.out.println("Test 3: " + sol.calculate(" 3+5 / 2 ") + " | Expected: 5");
        System.out.println("Test 4: " + sol.calculate("14-3/2") + " | Expected: 13");
    }
}
