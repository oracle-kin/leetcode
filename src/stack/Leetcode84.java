package stack;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 * Difficulty: Hard
 * Tags: Array, Stack, Monotonic Stack
 * URL: https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Problem Description:
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle
 * in the histogram.
 */
public class Leetcode84 {

    /**
     * Time: O(n)  — each element is pushed and popped at most once
     * Space: O(n) — monotonic stack
     *
     * Approach: Use a monotonic increasing stack of indices. When a bar is
     * shorter than the bar at the top of the stack, pop and compute the area
     * with the popped height as the limiting height.
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Leetcode84 sol = new Leetcode84();

        System.out.println("Test 1: " + sol.largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }) + " | Expected: 10");
        System.out.println("Test 2: " + sol.largestRectangleArea(new int[] { 2, 4 }) + " | Expected: 4");
        System.out.println("Test 3: " + sol.largestRectangleArea(new int[] { 1 }) + " | Expected: 1");
    }
}
