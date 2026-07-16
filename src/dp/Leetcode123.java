package dp;

/**
 * 123. Best Time to Buy and Sell Stock III
 * Difficulty: Hard
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Problem Description:
 * Find the maximum profit with at most two transactions.
 */
public class Leetcode123 {
    /**
     * DP: track 4 states - buy1, sell1, buy2, sell2 (max money after each action).
     * buy1 = max(buy1, -price), sell1 = max(sell1, buy1+price)
     * buy2 = max(buy2, sell1-price), sell2 = max(sell2, buy2+price)
     * Time: O(n), Space: O(1)
     */
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }

    public static void main(String[] args) {
        Leetcode123 sol = new Leetcode123();
        System.out.println(sol.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // Expected: 6
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 4, 5}));          // Expected: 4
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));          // Expected: 0
        System.out.println(sol.maxProfit(new int[]{1}));                      // Expected: 0
    }
}
