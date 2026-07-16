package dp;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * Difficulty: Hard
 * Tags: Array, Dynamic Programming
 * URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * Problem Description:
 * Find the maximum profit with at most k transactions.
 */
public class Leetcode188 {
    /**
     * DP: If k >= n/2, same as unlimited transactions (Leetcode122).
     * Otherwise, dp[t][d] = max profit using at most t transactions up to day d.
     * dp[t][d] = max(dp[t][d-1], max over j<d of (price[d] - price[j] + dp[t-1][j]))
     * Optimize inner loop by tracking maxPrev = max(dp[t-1][j] - price[j]).
     * Time: O(k*n), Space: O(k*n) -> O(n) with 1D optimization
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // If k >= n/2, unlimited transactions (same as Leetcode122)
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            }
            return profit;
        }

        int[][] dp = new int[k + 1][n];
        for (int t = 1; t <= k; t++) {
            int maxPrev = -prices[0];
            for (int d = 1; d < n; d++) {
                dp[t][d] = Math.max(dp[t][d - 1], prices[d] + maxPrev);
                maxPrev = Math.max(maxPrev, dp[t - 1][d] - prices[d]);
            }
        }
        return dp[k][n - 1];
    }

    public static void main(String[] args) {
        Leetcode188 sol = new Leetcode188();
        System.out.println(sol.maxProfit(2, new int[]{2, 4, 1}));              // Expected: 2
        System.out.println(sol.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));    // Expected: 7
        System.out.println(sol.maxProfit(2, new int[]{1, 2, 3, 4, 5}));       // Expected: 4
    }
}
