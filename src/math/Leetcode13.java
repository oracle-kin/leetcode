package math;

/**
 * 13. Roman to Integer
 * Difficulty: Easy
 * Tags: Hash Table, Math, String
 * URL: https://leetcode.com/problems/roman-to-integer/
 *
 * Problem Description:
 * Convert a roman numeral to an integer. Input is from 1 to 3999.
 *
 * Complexity: O(n) time, O(1) space
 * Algorithm: Traverse from left to right. If the current value is less than
 * the next value (subtractive notation like IV, IX), subtract it; otherwise add it.
 */
public class Leetcode13 {
    public int romanToInt(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = getValue(s.charAt(i));

            if (i + 1 < s.length() && current < getValue(s.charAt(i + 1))) {
                result -= current;
            } else {
                result += current;
            }
        }

        return result;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        Leetcode13 sol = new Leetcode13();

        // Test case 1
        System.out.println("Test 1: " + sol.romanToInt("III")); // Expected: 3

        // Test case 2
        System.out.println("Test 2: " + sol.romanToInt("LVIII")); // Expected: 58

        // Test case 3
        System.out.println("Test 3: " + sol.romanToInt("MCMXCIV")); // Expected: 1994

        // Test case 4
        System.out.println("Test 4: " + sol.romanToInt("IV")); // Expected: 4

        // Test case 5
        System.out.println("Test 5: " + sol.romanToInt("MMMCMXCIX")); // Expected: 3999
    }
}
