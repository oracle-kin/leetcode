package stack;

import java.util.Stack;

/**
 * 155. Min Stack
 * Difficulty: Medium
 * Tags: Stack, Design
 * URL: https://leetcode.com/problems/min-stack/
 *
 * Problem Description:
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time. Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 */
public class Leetcode155 {

    /**
     * All operations: O(1) time, O(n) space
     *
     * Approach: Use two stacks — one for values, one for current minimums.
     */
    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("getMin(): " + minStack.getMin() + " | Expected: -3");
        minStack.pop();
        System.out.println("top(): " + minStack.top() + " | Expected: 0");
        System.out.println("getMin(): " + minStack.getMin() + " | Expected: -2");
    }
}
