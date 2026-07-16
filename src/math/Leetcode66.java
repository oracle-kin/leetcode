package math;

import java.util.Arrays;

/**
 * 66. Plus One
 * Difficulty: Easy
 * Tags: Array, Math
 * URL: https://leetcode.com/problems/plus-one/
 *
 * Problem Description:
 * Increment the large integer represented as an array of digits by one.
 * The digits are stored such that the most significant digit is at the
 * head of the list.
 *
 * Complexity: O(n) time, O(1) space (or O(n) for result with carry overflow)
 * Algorithm: Traverse from right to left. Add 1 to the least significant digit
 * and propagate the carry. If carry survives past the most significant digit,
 * create a new array with an extra leading 1.
 */
public class Leetcode66 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // All digits were 9; need a new array with leading 1
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        Leetcode66 sol = new Leetcode66();

        // Test case 1
        int[] digits1 = {1, 2, 3};
        System.out.println("Test 1: " + Arrays.toString(sol.plusOne(digits1))); // Expected: [1, 2, 4]

        // Test case 2
        int[] digits2 = {4, 3, 2, 1};
        System.out.println("Test 2: " + Arrays.toString(sol.plusOne(digits2))); // Expected: [4, 3, 2, 2]

        // Test case 3
        int[] digits3 = {9};
        System.out.println("Test 3: " + Arrays.toString(sol.plusOne(digits3))); // Expected: [1, 0]

        // Test case 4
        int[] digits4 = {9, 9, 9};
        System.out.println("Test 4: " + Arrays.toString(sol.plusOne(digits4))); // Expected: [1, 0, 0, 0]

        // Test case 5
        int[] digits5 = {0};
        System.out.println("Test 5: " + Arrays.toString(sol.plusOne(digits5))); // Expected: [1]
    }
}
