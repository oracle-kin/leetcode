package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. Gray Code
 * Difficulty: Medium
 * Tags: Math, Backtracking, Bit Manipulation
 * URL: https://leetcode.com/problems/gray-code/
 *
 * Problem Description:
 * Return any n-bit Gray code sequence. Gray code sequence has adjacent numbers
 * differing by exactly one bit.
 *
 * Complexity: O(2^N) time, O(2^N) space.
 * Uses formula: G(i) = i ^ (i >> 1)
 */
public class Leetcode89 {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int size = 1 << n;
        for (int i = 0; i < size; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode89 sol = new Leetcode89();

        // Test 1: n=2 => [0,1,3,2]
        System.out.println("Test 1 (n=2): " + sol.grayCode(2));

        // Test 2: n=1 => [0,1]
        System.out.println("Test 2 (n=1): " + sol.grayCode(1));

        // Test 3: n=3 => [0,1,3,2,6,7,5,4]
        System.out.println("Test 3 (n=3): " + sol.grayCode(3));
    }
}
