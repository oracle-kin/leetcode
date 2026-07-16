package bit;

/**
 * 67. Add Binary
 * Difficulty: Easy
 * Tags: Math, String, Bit Manipulation, Simulation
 * URL: https://leetcode.com/problems/add-binary/
 *
 * Problem Description:
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Complexity: O(max(m, n)) time, O(1) extra space (excluding output)
 * Algorithm: Simulate binary addition from right to left with a carry.
 * Process digits from both strings, adding them along with the carry,
 * and build the result string.
 */
public class Leetcode67 {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            sb.append(sum % 2);
            carry = sum / 2;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Leetcode67 sol = new Leetcode67();

        // Test case 1
        System.out.println("Test 1: " + sol.addBinary("11", "1")); // Expected: "100"

        // Test case 2
        System.out.println("Test 2: " + sol.addBinary("1010", "1011")); // Expected: "10101"

        // Test case 3
        System.out.println("Test 3: " + sol.addBinary("0", "0")); // Expected: "0"

        // Test case 4
        System.out.println("Test 4: " + sol.addBinary("111", "111")); // Expected: "1110"

        // Test case 5
        System.out.println("Test 5: " + sol.addBinary("1", "111")); // Expected: "1000"
    }
}
