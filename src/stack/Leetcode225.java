package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * Difficulty: Easy
 * Tags: Stack, Design, Queue
 * URL: https://leetcode.com/problems/implement-stack-using-queues/
 *
 * Problem Description:
 * Implement a last-in-first-out (LIFO) stack using only two queues. The
 * implemented stack should support all the functions of a normal stack
 * (push, top, pop, empty).
 */
public class Leetcode225 {

    /**
     * push: O(n), pop/top/empty: O(1)
     * Space: O(n)
     *
     * Approach: Use two queues. On push, add to queue2, then move all
     * elements from queue1 to queue2, and swap references. This ensures
     * the newest element is always at the front of queue1.
     */
    static class MyStack {
        private Queue<Integer> q1;
        private Queue<Integer> q2;

        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        public void push(int x) {
            q2.offer(x);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            // Swap references
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println("top(): " + stack.top() + " | Expected: 2");
        System.out.println("pop(): " + stack.pop() + " | Expected: 2");
        System.out.println("empty(): " + stack.empty() + " | Expected: false");
        System.out.println("pop(): " + stack.pop() + " | Expected: 1");
        System.out.println("empty(): " + stack.empty() + " | Expected: true");
    }
}
