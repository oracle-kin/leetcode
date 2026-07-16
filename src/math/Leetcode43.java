package math;

/**
 * 43. Multiply Strings
 * Difficulty: Medium
 * Tags: Math, String, Simulation
 * URL: https://leetcode.com/problems/multiply-strings/
 *
 * Problem Description:
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product as a string. Do not use built-in BigInteger or
 * convert inputs to integer directly.
 *
 * Complexity: O(m * n) time, O(m + n) space
 * Algorithm: Grade-school multiplication. Use an array of size m+n to store
 * intermediate products. For each digit pair, compute the product and add
 * it to the appropriate position in the result array, handling carries.
 */
public class Leetcode43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Leetcode43 sol = new Leetcode43();

        // Test case 1
        System.out.println("Test 1: " + sol.multiply("2", "3")); // Expected: "6"

        // Test case 2
        System.out.println("Test 2: " + sol.multiply("123", "456")); // Expected: "56088"

        // Test case 3
        System.out.println("Test 3: " + sol.multiply("0", "123")); // Expected: "0"

        // Test case 4
        System.out.println("Test 4: " + sol.multiply("99", "99")); // Expected: "9801"

        // Test case 5
        System.out.println("Test 5: " + sol.multiply("123456789", "987654321"));
        // Expected: "121932631112635269"
    }
}
