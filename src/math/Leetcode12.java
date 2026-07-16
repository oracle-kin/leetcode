package math;

/**
 * 12. Integer to Roman
 * Difficulty: Medium
 * Tags: Hash Table, Math, String
 * URL: https://leetcode.com/problems/integer-to-roman/
 *
 * Problem Description:
 * Convert an integer to a roman numeral. Input is from 1 to 3999.
 *
 * Complexity: O(1) time, O(1) space (bounded input range)
 * Algorithm: Greedy approach. Use arrays of values and corresponding roman
 * symbols in descending order. Subtract the largest possible value and
 * append the corresponding symbol, repeating until the number is 0.
 */
public class Leetcode12 {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Leetcode12 sol = new Leetcode12();

        // Test case 1
        System.out.println("Test 1: " + sol.intToRoman(3)); // Expected: "III"

        // Test case 2
        System.out.println("Test 2: " + sol.intToRoman(58)); // Expected: "LVIII"

        // Test case 3
        System.out.println("Test 3: " + sol.intToRoman(1994)); // Expected: "MCMXCIV"

        // Test case 4
        System.out.println("Test 4: " + sol.intToRoman(4)); // Expected: "IV"

        // Test case 5
        System.out.println("Test 5: " + sol.intToRoman(3999)); // Expected: "MMMCMXCIX"
    }
}
