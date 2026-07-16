package math;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 * Difficulty: Hard
 * Tags: Math, Recursion
 * URL: https://leetcode.com/problems/permutation-sequence/
 *
 * Problem Description:
 * Return the kth permutation sequence of the set [1, 2, 3, ..., n].
 * Given n between 1 and 9, and k between 1 and n!.
 *
 * Complexity: O(n^2) time, O(n) space
 * Algorithm: Factorial number system. Determine each digit by calculating
 * how many (n-1)! blocks the kth permutation falls into. Use a list of
 * available numbers and remove each selected number.
 */
public class Leetcode60 {
    public String getPermutation(int n, int k) {
        // Precompute factorials
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // List of numbers to pick from
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Convert k to 0-indexed
        k--;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k -= index * factorial[n - i];
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Leetcode60 sol = new Leetcode60();

        // Test case 1
        System.out.println("Test 1: " + sol.getPermutation(3, 3)); // Expected: "213"

        // Test case 2
        System.out.println("Test 2: " + sol.getPermutation(4, 9)); // Expected: "2314"

        // Test case 3
        System.out.println("Test 3: " + sol.getPermutation(3, 1)); // Expected: "123"

        // Test case 4
        System.out.println("Test 4: " + sol.getPermutation(1, 1)); // Expected: "1"

        // Test case 5
        System.out.println("Test 5: " + sol.getPermutation(3, 6)); // Expected: "321"
    }
}
