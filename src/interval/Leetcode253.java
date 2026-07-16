package interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Greedy, Sorting, Heap, Prefix Sum
 * URL: https://leetcode.com/problems/meeting-rooms-ii/
 *
 * Problem Description:
 * Find the minimum number of conference rooms required given meeting time intervals. (Premium)
 */
public class Leetcode253 {

    /**
     * Min-heap of end times. Sort by start time, for each meeting poll completed
     * meetings from heap, add current meeting, track max heap size.
     * Time: O(n log n) — sorting + heap operations
     * Space: O(n)     — heap
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // If the earliest ending meeting has ended, free the room
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.offer(intervals[i][1]);
        }

        return heap.size();
    }

    public static void main(String[] args) {
        Leetcode253 sol = new Leetcode253();

        // Test 1
        System.out.println("Test 1: " + sol.minMeetingRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } }) + " | Expected: 2");

        // Test 2
        System.out.println("Test 2: " + sol.minMeetingRooms(new int[][] { { 7, 10 }, { 2, 4 } }) + " | Expected: 1");

        // Test 3
        System.out.println("Test 3: " + sol.minMeetingRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 }, { 5, 15 }, { 12, 30 } }) + " | Expected: 3");
    }
}
