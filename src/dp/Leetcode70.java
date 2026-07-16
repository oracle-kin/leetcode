package dp;

/**
 * 70. Climbing Stairs
 * Difficulty: Easy
 * Tags: Math, Dynamic Programming, Memoization
 * URL: https://leetcode.com/problems/climbing-stairs/
 *
 * Problem Description:
 * You are climbing a staircase. It takes n steps to reach the top. Each time you can climb 1 or 2 steps. How many distinct ways?
 */
public class Leetcode70 {
    /**
     * Fibonacci DP | O(n) time, O(1) space
     * dp[i] = dp[i-1] + dp[i-2] (i steps = 1 step from i-1 or 2 steps from i-2)
     * Use two variables to optimize space.
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Leetcode70 sol = new Leetcode70();
        System.out.println(sol.climbStairs(2));  // 2
        System.out.println(sol.climbStairs(3));  // 3
        System.out.println(sol.climbStairs(4));  // 5
        System.out.println(sol.climbStairs(5));  // 8
        System.out.println(sol.climbStairs(10)); // 89
    }
}
