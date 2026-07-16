package stack;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * Difficulty: Medium
 * Tags: Array, Math, Stack
 * URL: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Problem Description:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, and /. Each operand may be an integer or
 * another expression. Division between two integers truncates toward zero.
 */
public class Leetcode150 {

    /**
     * Time: O(n)  — single pass through tokens
     * Space: O(n) — stack stores operands
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Leetcode150 sol = new Leetcode150();

        System.out.println("Test 1: " + sol.evalRPN(new String[] { "2", "1", "+", "3", "*" }) + " | Expected: 9");
        System.out.println("Test 2: " + sol.evalRPN(new String[] { "4", "13", "5", "/", "+" }) + " | Expected: 6");
        System.out.println("Test 3: " + sol.evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }) + " | Expected: 22");
    }
}
