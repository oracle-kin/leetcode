package slidingwindow;

/**
 * 209. Minimum Size Subarray Sum
 * Difficulty: Medium
 * Tags: Array, Binary Search, Sliding Window, Prefix Sum
 * URL: https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Problem Description:
 * Find the minimal length of a contiguous subarray with sum >= target.
 */
public class Leetcode209 {

    /**
     * Sliding window: expand right to increase sum, contract left to minimize length.
     * Time: O(n)  — each element added and removed at most once
     * Space: O(1) — only primitives
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        Leetcode209 sol = new Leetcode209();

        // Test 1
        System.out.println("Test 1: " + sol.minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }) + " | Expected: 2");

        // Test 2
        System.out.println("Test 2: " + sol.minSubArrayLen(4, new int[] { 1, 4, 4 }) + " | Expected: 1");

        // Test 3
        System.out.println("Test 3: " + sol.minSubArrayLen(11, new int[] { 1, 1, 1, 1, 1, 1, 1, 1 }) + " | Expected: 0");
    }
}
