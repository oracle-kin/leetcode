package dp;

/**
 * 213. House Robber II
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/house-robber-ii/
 *
 * Problem Description:
 * Same as House Robber but houses are arranged in a circle. Adjacent means first and last are also adjacent.
 */
public class Leetcode213 {
    /**
     * DP: Since houses are in a circle, rob either nums[0..n-2] or nums[1..n-1].
     * Use the same linear rob logic (Leetcode198) on both subarrays.
     * Time: O(n), Space: O(1)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        if (start > end) return 0;
        if (start == end) return nums[start];
        int prev2 = nums[start];
        int prev1 = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        Leetcode213 sol = new Leetcode213();
        System.out.println(sol.rob(new int[]{2, 3, 2}));     // Expected: 3
        System.out.println(sol.rob(new int[]{1, 2, 3, 1}));  // Expected: 4
        System.out.println(sol.rob(new int[]{1, 2, 3}));     // Expected: 3
        System.out.println(sol.rob(new int[]{1}));           // Expected: 1
    }
}
