package interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * Difficulty: Medium
 * Tags: Array
 * URL: https://leetcode.com/problems/insert-interval/
 *
 * Problem Description:
 * Insert a new interval into a set of non-overlapping intervals and merge if necessary.
 */
public class Leetcode57 {

    /**
     * Three-phase approach: add non-overlapping before, merge overlapping, add remaining.
     * Time: O(n)  — single pass
     * Space: O(n) — output list
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Add all intervals before newInterval (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Add remaining intervals after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Leetcode57 sol = new Leetcode57();

        // Test 1
        int[][] res1 = sol.insert(new int[][] { { 1, 3 }, { 6, 9 } }, new int[] { 2, 5 });
        System.out.print("Test 1: ");
        printIntervals(res1);
        System.out.println(" | Expected: [[1,5],[6,9]]");

        // Test 2
        int[][] res2 = sol.insert(new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } }, new int[] { 4, 8 });
        System.out.print("Test 2: ");
        printIntervals(res2);
        System.out.println(" | Expected: [[1,2],[3,10],[12,16]]");

        // Test 3 (empty)
        int[][] res3 = sol.insert(new int[][] {}, new int[] { 5, 7 });
        System.out.print("Test 3: ");
        printIntervals(res3);
        System.out.println(" | Expected: [[5,7]]");
    }

    private static void printIntervals(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
        }
    }
}
