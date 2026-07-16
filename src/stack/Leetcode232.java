package stack;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * Difficulty: Easy
 * Tags: Stack, Design, Queue
 * URL: https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * Problem Description:
 * Implement a first-in-first-out (FIFO) queue using only two stacks. The
 * implemented queue should support all the functions of a normal queue
 * (push, peek, pop, empty).
 */
public class Leetcode232 {

    /**
     * push: O(1), pop/peek: amortized O(1)
     * Space: O(n)
     *
     * Approach: Use an input stack for push and an output stack for pop/peek.
     * When output stack is empty, transfer all elements from input stack.
     */
    static class MyQueue {
        private Stack<Integer> input;
        private Stack<Integer> output;

        public MyQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }

        public void push(int x) {
            input.push(x);
        }

        public int pop() {
            if (output.isEmpty()) {
                transfer();
            }
            return output.pop();
        }

        public int peek() {
            if (output.isEmpty()) {
                transfer();
            }
            return output.peek();
        }

        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }

        private void transfer() {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("peek(): " + queue.peek() + " | Expected: 1");
        System.out.println("pop(): " + queue.pop() + " | Expected: 1");
        System.out.println("empty(): " + queue.empty() + " | Expected: false");
        System.out.println("pop(): " + queue.pop() + " | Expected: 2");
        System.out.println("empty(): " + queue.empty() + " | Expected: true");
    }
}
