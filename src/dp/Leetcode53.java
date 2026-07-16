package dp;

/**
 * 53. Maximum Subarray
 * Difficulty: Medium
 * Tags: Array, Divide and Conquer, Dynamic Programming
 * URL: https://leetcode.com/problems/maximum-subarray/
 *
 * Problem Description:
 * Find the contiguous subarray with the largest sum (Kadane's algorithm).
 */
public class Leetcode53 {
    /**
     * Kadane's Algorithm | O(n) time, O(1) space
     * Maintain current subarray sum curSum and global max maxSum.
     * At each step: curSum = max(num, curSum + num), maxSum = max(maxSum, curSum)
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Leetcode53 sol = new Leetcode53();
        System.out.println(sol.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(sol.maxSubArray(new int[]{1}));         // 1
        System.out.println(sol.maxSubArray(new int[]{5,4,-1,7,8})); // 23
        System.out.println(sol.maxSubArray(new int[]{-1}));        // -1
    }
}
