package bit;

/**
 * 190. Reverse Bits
 * Difficulty: Easy
 * Tags: Bit Manipulation, Divide and Conquer
 * URL: https://leetcode.com/problems/reverse-bits/
 *
 * Problem Description:
 * Reverse bits of a given 32-bit unsigned integer.
 *
 * Complexity: O(1) time, O(1) space (fixed 32 iterations)
 * Algorithm: Process each bit from right to left. For each bit of n,
 * shift it to its reversed position in the result. Iterate 32 times.
 */
public class Leetcode190 {
    // You need to treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>>= 1; // Unsigned right shift
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode190 sol = new Leetcode190();

        // Test case 1: 43261596 (00000010100101000001111010011100) -> 964176192 (00111001011110000010100101000000)
        System.out.println("Test 1: " + sol.reverseBits(0b00000010100101000001111010011100));
        // Expected: 964176192

        // Test case 2: -3 (11111111111111111111111111111101) -> -1073741825 (10111111111111111111111111111111)
        System.out.println("Test 2: " + sol.reverseBits(0b11111111111111111111111111111101));
        // Expected: -1073741825

        // Test case 3
        System.out.println("Test 3: " + sol.reverseBits(0)); // Expected: 0

        // Test case 4
        System.out.println("Test 4: " + sol.reverseBits(1)); // Expected: -2147483648 (1 << 31)

        // Test case 5
        System.out.println("Test 5: " + sol.reverseBits(0b11111111111111111111111111111111));
        // Expected: -1 (all bits reversed = all ones)
    }
}
