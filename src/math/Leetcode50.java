package math;

/**
 * 50. Pow(x, n)
 * Difficulty: Medium
 * Tags: Math, Recursion
 * URL: https://leetcode.com/problems/powx-n/
 *
 * Problem Description:
 * Implement pow(x, n) which calculates x raised to the power n (x^n).
 *
 * Complexity: O(log n) time, O(log n) space (recursion) or O(1) iterative
 * Algorithm: Fast exponentiation (binary exponentiation). Use the property:
 * - If n is even: x^n = (x^(n/2))^2
 * - If n is odd: x^n = x * (x^(n/2))^2
 * Handle negative exponents by computing 1 / pow(x, -n).
 */
public class Leetcode50 {
    public double myPow(double x, int n) {
        // Handle negative exponent
        long N = n; // Use long to handle Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        double result = 1.0;

        while (n > 0) {
            if ((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n >>= 1;
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode50 sol = new Leetcode50();

        // Test case 1
        System.out.println("Test 1: " + sol.myPow(2.0, 10)); // Expected: 1024.0

        // Test case 2
        System.out.println("Test 2: " + sol.myPow(2.1, 3)); // Expected: 9.261

        // Test case 3
        System.out.println("Test 3: " + sol.myPow(2.0, -2)); // Expected: 0.25

        // Test case 4
        System.out.println("Test 4: " + sol.myPow(1.0, -2147483648)); // Expected: 1.0

        // Test case 5
        System.out.println("Test 5: " + sol.myPow(2.0, -2147483648)); // Expected: 0.0
    }
}
