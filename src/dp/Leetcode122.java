package dp;

/**
 * 122. Best Time to Buy and Sell Stock II
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Greedy
 * URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Problem Description:
 * Find the maximum profit with as many transactions as you like (buy one and sell one share multiple times).
 */
public class Leetcode122 {
    /**
     * Greedy: sum all positive price differences.
     * Time: O(n), Space: O(1)
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        Leetcode122 sol = new Leetcode122();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));  // Expected: 7
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 4, 5}));     // Expected: 4
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));     // Expected: 0
    }
}
