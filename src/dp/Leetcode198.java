package dp;

/**
 * 198. House Robber
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/house-robber/
 *
 * Problem Description:
 * Determine the maximum amount of money you can rob without alerting the police (no adjacent houses).
 */
public class Leetcode198 {
    /**
     * DP: dp[i] = max(dp[i-1], dp[i-2] + nums[i]).
     * Space-optimized: use two variables.
     * Time: O(n), Space: O(1)
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Leetcode198 sol = new Leetcode198();
        System.out.println(sol.rob(new int[]{1, 2, 3, 1}));     // Expected: 4
        System.out.println(sol.rob(new int[]{2, 7, 9, 3, 1}));  // Expected: 12
        System.out.println(sol.rob(new int[]{2, 1, 1, 2}));     // Expected: 4
        System.out.println(sol.rob(new int[]{0}));              // Expected: 0
    }
}
