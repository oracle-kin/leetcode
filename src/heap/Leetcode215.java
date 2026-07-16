package heap;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * Difficulty: Medium
 * Tags: Array, Divide and Conquer, Sorting, Heap, Quickselect
 * URL: https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Problem Description:
 * Find the kth largest element in an unsorted array.
 */
public class Leetcode215 {

    /**
     * Min-heap of size k. After processing all elements, peek is kth largest.
     * Time: O(n log k) — each heap operation is log k
     * Space: O(k)     — heap stores k elements
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        Leetcode215 sol = new Leetcode215();

        // Test 1
        System.out.println("Test 1: " + sol.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2) + " | Expected: 5");

        // Test 2
        System.out.println("Test 2: " + sol.findKthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4) + " | Expected: 4");

        // Test 3
        System.out.println("Test 3: " + sol.findKthLargest(new int[] { 1 }, 1) + " | Expected: 1");
    }
}
