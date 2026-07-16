package bit;

/**
 * 29. Divide Two Integers
 * Difficulty: Medium
 * Tags: Math, Bit Manipulation
 * URL: https://leetcode.com/problems/divide-two-integers/
 *
 * Problem Description:
 * Divide two integers without using multiplication, division, and mod operator.
 * Truncate toward zero. Handle overflow: when dividend = Integer.MIN_VALUE
 * and divisor = -1, return Integer.MAX_VALUE.
 *
 * Complexity: O(log n) time, O(1) space
 * Algorithm: Use bit manipulation and subtraction. Repeatedly subtract the largest
 * shifted divisor (divisor << shift) from the dividend, accumulating the quotient
 * via powers of 2. Handle signs separately.
 */
public class Leetcode29 {
    public int divide(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to positive (use long for overflow safety with MIN_VALUE)
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;

        while (absDividend >= absDivisor) {
            long temp = absDivisor;
            int multiple = 1;

            while (absDividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            absDividend -= temp;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }

    public static void main(String[] args) {
        Leetcode29 sol = new Leetcode29();

        // Test case 1
        System.out.println("Test 1: " + sol.divide(10, 3)); // Expected: 3

        // Test case 2
        System.out.println("Test 2: " + sol.divide(7, -3)); // Expected: -2

        // Test case 3
        System.out.println("Test 3: " + sol.divide(0, 1)); // Expected: 0

        // Test case 4
        System.out.println("Test 4: " + sol.divide(-2147483648, -1)); // Expected: 2147483647

        // Test case 5
        System.out.println("Test 5: " + sol.divide(1, 1)); // Expected: 1
    }
}
