package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 239. Sliding Window Maximum
 * Difficulty: Hard
 * Tags: Array, Queue, Sliding Window, Heap, Monotonic Queue
 * URL: https://leetcode.com/problems/sliding-window-maximum/
 *
 * Problem Description:
 * Return the max sliding window for each window of size k.
 */
public class Leetcode239 {

    /**
     * Monotonic decreasing deque. Store indices; for each new element,
     * remove from back while smaller, remove from front if out of window.
     * Time: O(n)  — each element pushed and popped at most once
     * Space: O(k) — deque stores at most k elements
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove indices outside the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements from the back
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Window is fully formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode239 sol = new Leetcode239();

        // Test 1
        System.out.println("Test 1: " + Arrays.toString(sol.maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)) + " | Expected: [3, 3, 5, 5, 6, 7]");

        // Test 2
        System.out.println("Test 2: " + Arrays.toString(sol.maxSlidingWindow(new int[] { 1 }, 1)) + " | Expected: [1]");

        // Test 3
        System.out.println("Test 3: " + Arrays.toString(sol.maxSlidingWindow(new int[] { 1, -1 }, 1)) + " | Expected: [1, -1]");

        // Test 4
        System.out.println("Test 4: " + Arrays.toString(sol.maxSlidingWindow(new int[] { 7, 2, 4 }, 2)) + " | Expected: [7, 4]");
    }
}
