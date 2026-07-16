package dp;

/**
 * 121. Best Time to Buy and Sell Stock
 * Difficulty: Easy
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Problem Description:
 * Find the maximum profit from buying and selling one share of stock on different days.
 */
public class Leetcode121 {
    /**
     * Track min price and max profit in one pass.
     * Time: O(n), Space: O(1)
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Leetcode121 sol = new Leetcode121();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // Expected: 5
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));   // Expected: 0
        System.out.println(sol.maxProfit(new int[]{1, 2}));            // Expected: 1
    }
}
