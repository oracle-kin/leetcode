package string;

/**
 * 65. Valid Number
 * Difficulty: Hard
 * Tags: String
 * URL: https://leetcode.com/problems/valid-number/
 *
 * Problem Description:
 * Given a string s, return whether s is a valid number.
 */
public class Leetcode65 {

    /**
     * Finite state machine with flags for digit, dot, exponent seen.
     * Time: O(n)  — single pass
     * Space: O(1) — only flags
     */
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;

        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                if (seenDot || seenExponent) return false;
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (seenExponent || !seenDigit) return false;
                seenExponent = true;
                seenDigit = false; // need digits after exponent
            } else {
                return false;
            }
        }

        return seenDigit;
    }

    public static void main(String[] args) {
        Leetcode65 sol = new Leetcode65();

        // Valid numbers
        System.out.println("Test 1 (0): " + sol.isNumber("0") + " | Expected: true");
        System.out.println("Test 2 (2e10): " + sol.isNumber("2e10") + " | Expected: true");
        System.out.println("Test 3 (-90E3): " + sol.isNumber("-90E3") + " | Expected: true");
        System.out.println("Test 4 (53.5e93): " + sol.isNumber("53.5e93") + " | Expected: true");

        // Invalid numbers
        System.out.println("Test 5 (e): " + sol.isNumber("e") + " | Expected: false");
        System.out.println("Test 6 (abc): " + sol.isNumber("abc") + " | Expected: false");
        System.out.println("Test 7 (1e): " + sol.isNumber("1e") + " | Expected: false");
        System.out.println("Test 8 (e3): " + sol.isNumber("e3") + " | Expected: false");
        System.out.println("Test 9 (99e2.5): " + sol.isNumber("99e2.5") + " | Expected: false");
    }
}
