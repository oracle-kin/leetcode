package math;

/**
 * 9. Palindrome Number
 * Difficulty: Easy
 * Tags: Math
 * URL: https://leetcode.com/problems/palindrome-number/
 *
 * Problem Description:
 * Given an integer x, return true if x is a palindrome, false otherwise.
 * Do not convert the integer to a string.
 *
 * Complexity: O(log10(x)) time, O(1) space
 * Algorithm: Reverse half of the number and compare with the other half.
 * Negative numbers are never palindromes. Stop when the reversed half
 * becomes >= the remaining half.
 */
public class Leetcode9 {
    public boolean isPalindrome(int x) {
        // Negative numbers and numbers ending with 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even digits: x == reversedHalf
        // For odd digits: x == reversedHalf / 10 (drop the middle digit)
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        Leetcode9 sol = new Leetcode9();

        // Test case 1
        System.out.println("Test 1: " + sol.isPalindrome(121)); // Expected: true

        // Test case 2
        System.out.println("Test 2: " + sol.isPalindrome(-121)); // Expected: false

        // Test case 3
        System.out.println("Test 3: " + sol.isPalindrome(10)); // Expected: false

        // Test case 4
        System.out.println("Test 4: " + sol.isPalindrome(0)); // Expected: true

        // Test case 5
        System.out.println("Test 5: " + sol.isPalindrome(12321)); // Expected: true
    }
}
