package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals
 * Difficulty: Medium
 * Tags: Array, Sorting
 * URL: https://leetcode.com/problems/merge-intervals/
 *
 * Problem Description:
 * Merge all overlapping intervals and return an array of non-overlapping intervals.
 */
public class Leetcode56 {

    /**
     * Sort by start time, then merge overlapping intervals in one pass.
     * Time: O(n log n) — sorting
     * Space: O(n)      — output list (or O(log n) for sorting stack)
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] curr = intervals[0];
        merged.add(curr);

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (curr[1] >= next[0]) {
                curr[1] = Math.max(curr[1], next[1]);
            } else {
                curr = next;
                merged.add(curr);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        Leetcode56 sol = new Leetcode56();

        // Test 1
        int[][] res1 = sol.merge(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } });
        System.out.print("Test 1: ");
        printIntervals(res1);
        System.out.println(" | Expected: [[1,6],[8,10],[15,18]]");

        // Test 2
        int[][] res2 = sol.merge(new int[][] { { 1, 4 }, { 4, 5 } });
        System.out.print("Test 2: ");
        printIntervals(res2);
        System.out.println(" | Expected: [[1,5]]");

        // Test 3
        int[][] res3 = sol.merge(new int[][] { { 1, 4 }, { 0, 4 } });
        System.out.print("Test 3: ");
        printIntervals(res3);
        System.out.println(" | Expected: [[0,4]]");
    }

    private static void printIntervals(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
        }
    }
}
