package bit;

/**
 * 191. Number of 1 Bits
 * Difficulty: Easy
 * Tags: Bit Manipulation, Divide and Conquer
 * URL: https://leetcode.com/problems/number-of-1-bits/
 *
 * Problem Description:
 * Return the number of '1' bits (Hamming weight) in an unsigned integer.
 *
 * Complexity: O(k) time where k is the number of 1 bits (O(1) worst-case);
 * O(1) space
 * Algorithm: Use n & (n - 1) trick which clears the lowest set bit.
 * Count how many times this operation can be applied until n becomes 0.
 */
public class Leetcode191 {
    // You need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            n &= (n - 1); // Clear the lowest set bit
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Leetcode191 sol = new Leetcode191();

        // Test case 1: 11 (1011) -> 3
        System.out.println("Test 1: " + sol.hammingWeight(11)); // Expected: 3

        // Test case 2: 128 (10000000) -> 1
        System.out.println("Test 2: " + sol.hammingWeight(128)); // Expected: 1

        // Test case 3: -3 (11111111111111111111111111111101) -> 31
        System.out.println("Test 3: " + sol.hammingWeight(-3)); // Expected: 31

        // Test case 4
        System.out.println("Test 4: " + sol.hammingWeight(0)); // Expected: 0

        // Test case 5
        System.out.println("Test 5: " + sol.hammingWeight(1)); // Expected: 1
    }
}
