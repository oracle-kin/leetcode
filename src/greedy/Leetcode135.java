package greedy;

import java.util.Arrays;

/**
 * 135. Candy
 * Difficulty: Hard
 * Tags: Array, Greedy
 * URL: https://leetcode.com/problems/candy/
 *
 * Problem Description:
 * Distribute candies to children with ratings, each child gets at least one candy and higher rated children get more than neighbors.
 */
public class Leetcode135 {

    /**
     * Two-pass greedy: left-to-right ensures right neighbor gets more if higher rated;
     * right-to-left ensures left neighbor gets more if higher rated.
     * Time: O(n)  — two passes
     * Space: O(n) — candies array
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // Left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;
        for (int c : candies) total += c;
        return total;
    }

    public static void main(String[] args) {
        Leetcode135 sol = new Leetcode135();

        // Test 1
        System.out.println("Test 1: " + sol.candy(new int[] { 1, 0, 2 }) + " | Expected: 5");

        // Test 2
        System.out.println("Test 2: " + sol.candy(new int[] { 1, 2, 2 }) + " | Expected: 4");

        // Test 3
        System.out.println("Test 3: " + sol.candy(new int[] { 1, 3, 2, 2, 1 }) + " | Expected: 7");
    }
}
