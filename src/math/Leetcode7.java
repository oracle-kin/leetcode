package math;

/**
 * 7. Reverse Integer
 * Difficulty: Medium
 * Tags: Math
 * URL: https://leetcode.com/problems/reverse-integer/
 *
 * Problem Description:
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer
 * range [-2^31, 2^31 - 1], return 0.
 *
 * Complexity: O(log10(x)) time, O(1) space
 * Algorithm: Repeatedly pop the last digit of x using % 10 and push it to
 * the result. Check for overflow before each push by comparing against
 * Integer.MAX_VALUE / 10 and Integer.MIN_VALUE / 10.
 */
public class Leetcode7 {
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check overflow before multiplying
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode7 sol = new Leetcode7();

        // Test case 1
        System.out.println("Test 1: " + sol.reverse(123)); // Expected: 321

        // Test case 2
        System.out.println("Test 2: " + sol.reverse(-123)); // Expected: -321

        // Test case 3
        System.out.println("Test 3: " + sol.reverse(120)); // Expected: 21

        // Test case 4
        System.out.println("Test 4: " + sol.reverse(0)); // Expected: 0

        // Test case 5
        System.out.println("Test 5: " + sol.reverse(1534236469)); // Expected: 0 (overflow)
    }
}
