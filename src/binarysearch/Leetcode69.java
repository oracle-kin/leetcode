package binarysearch;

/**
 * 69. Sqrt(x)
 * Difficulty: Easy
 * Tags: Math, Binary Search
 * URL: https://leetcode.com/problems/sqrtx/
 *
 * Problem Description:
 * Compute and return the square root of x rounded down to the nearest integer.
 * Do not use any built-in exponent function or operator.
 *
 * Complexity: O(log x) time, O(1) space
 * Algorithm: Binary search in the range [0, x] (or [0, x/2+1] for x > 1).
 * Find the largest integer mid such that mid * mid <= x.
 */
public class Leetcode69 {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1, right = x / 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        Leetcode69 sol = new Leetcode69();

        // Test case 1
        System.out.println("Test 1: " + sol.mySqrt(4)); // Expected: 2

        // Test case 2
        System.out.println("Test 2: " + sol.mySqrt(8)); // Expected: 2

        // Test case 3
        System.out.println("Test 3: " + sol.mySqrt(0)); // Expected: 0

        // Test case 4
        System.out.println("Test 4: " + sol.mySqrt(1)); // Expected: 1

        // Test case 5
        System.out.println("Test 5: " + sol.mySqrt(2147395599)); // Expected: 46339
    }
}
