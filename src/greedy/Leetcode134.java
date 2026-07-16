package greedy;

/**
 * 134. Gas Station
 * Difficulty: Medium
 * Tags: Array, Greedy
 * URL: https://leetcode.com/problems/gas-station/
 *
 * Problem Description:
 * Return the starting gas station index if you can travel around the circuit once, or -1.
 */
public class Leetcode134 {

    /**
     * Greedy: if total gas >= total cost, a solution exists.
     * If running tank goes negative, reset start to next station.
     * Time: O(n)  — single pass
     * Space: O(1) — only primitives
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        Leetcode134 sol = new Leetcode134();

        // Test 1
        System.out.println("Test 1: " + sol.canCompleteCircuit(
            new int[] { 1, 2, 3, 4, 5 },
            new int[] { 3, 4, 5, 1, 2 }
        ) + " | Expected: 3");

        // Test 2
        System.out.println("Test 2: " + sol.canCompleteCircuit(
            new int[] { 2, 3, 4 },
            new int[] { 3, 4, 3 }
        ) + " | Expected: -1");

        // Test 3
        System.out.println("Test 3: " + sol.canCompleteCircuit(
            new int[] { 5, 1, 2, 3, 4 },
            new int[] { 4, 4, 1, 5, 1 }
        ) + " | Expected: 4");
    }
}
