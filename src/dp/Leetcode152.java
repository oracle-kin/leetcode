package dp;

/**
 * 152. Maximum Product Subarray
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/maximum-product-subarray/
 *
 * Problem Description:
 * Find the contiguous subarray within an array that has the largest product.
 */
public class Leetcode152 {
    /**
     * DP: track both max product and min product (handles negatives).
     * curMax = max(num, num*prevMax, num*prevMin)
     * curMin = min(num, num*prevMax, num*prevMin)
     * Time: O(n), Space: O(1)
     */
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int curMax = nums[0], curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int tempMax = Math.max(num, Math.max(curMax * num, curMin * num));
            curMin = Math.min(num, Math.min(curMax * num, curMin * num));
            curMax = tempMax;
            maxSoFar = Math.max(maxSoFar, curMax);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        Leetcode152 sol = new Leetcode152();
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4}));   // Expected: 6
        System.out.println(sol.maxProduct(new int[]{-2, 0, -1}));    // Expected: 0
        System.out.println(sol.maxProduct(new int[]{-2, 3, -4}));    // Expected: 24
        System.out.println(sol.maxProduct(new int[]{0, 2}));         // Expected: 2
    }
}
