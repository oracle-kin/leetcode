package string;

/**
 * 8. String to Integer (atoi)
 * Difficulty: Medium
 * Tags: String
 * URL: https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Problem Description:
 * Implement the myAtoi function that converts a string to a 32-bit signed integer.
 */
public class Leetcode8 {

    /**
     * Step-by-step parsing: skip whitespace, read sign, convert digits, clamp to 32-bit int.
     * Time: O(n)  — single pass
     * Space: O(1) — only primitives
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();

        // Skip whitespace
        while (i < n && s.charAt(i) == ' ') i++;
        if (i == n) return 0;

        // Read sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Convert digits
        long result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            if (sign == 1 && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            i++;
        }

        return (int) (sign * result);
    }

    public static void main(String[] args) {
        Leetcode8 sol = new Leetcode8();

        // Test 1
        System.out.println("Test 1: " + sol.myAtoi("42") + " | Expected: 42");

        // Test 2
        System.out.println("Test 2: " + sol.myAtoi("   -42") + " | Expected: -42");

        // Test 3
        System.out.println("Test 3: " + sol.myAtoi("4193 with words") + " | Expected: 4193");

        // Test 4
        System.out.println("Test 4: " + sol.myAtoi("-91283472332") + " | Expected: -2147483648");

        // Test 5
        System.out.println("Test 5: " + sol.myAtoi("") + " | Expected: 0");
    }
}
