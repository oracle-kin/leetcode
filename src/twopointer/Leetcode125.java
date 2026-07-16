package twopointer;

/**
 * 125. Valid Palindrome
 * Difficulty: Easy
 * Tags: Two Pointers, String
 * URL: https://leetcode.com/problems/valid-palindrome/
 *
 * Problem Description:
 * Return true if a string is a palindrome considering only alphanumeric characters and ignoring cases.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode125 {

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode125 sol = new Leetcode125();

        // Test case 1
        System.out.println("Test 1: " + sol.isPalindrome("A man, a plan, a canal: Panama"));
        // Expected: true

        // Test case 2
        System.out.println("Test 2: " + sol.isPalindrome("race a car"));
        // Expected: false

        // Test case 3
        System.out.println("Test 3: " + sol.isPalindrome(" "));
        // Expected: true
    }
}
