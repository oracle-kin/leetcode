package twopointer;

/**
 * 151. Reverse Words in a String
 * Difficulty: Medium
 * Tags: Two Pointers, String
 * URL: https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Problem Description:
 * Reverse the order of words in a string.
 * Remove leading/trailing spaces and reduce multiple spaces to single.
 *
 * Time: O(n) | Space: O(n) for output
 */
public class Leetcode151 {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        StringBuilder result = new StringBuilder();

        int right = n - 1;
        while (right >= 0) {
            // Skip trailing spaces
            while (right >= 0 && chars[right] == ' ') right--;

            if (right < 0) break;

            // Find start of word
            int left = right;
            while (left >= 0 && chars[left] != ' ') left--;

            // Append word + space
            if (result.length() > 0) result.append(' ');
            result.append(s, left + 1, right + 1);

            right = left;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Leetcode151 sol = new Leetcode151();

        // Test case 1
        System.out.println("Test 1: \"" + sol.reverseWords("the sky is blue") + "\"");
        // Expected: "blue is sky the"

        // Test case 2
        System.out.println("Test 2: \"" + sol.reverseWords("  hello world  ") + "\"");
        // Expected: "world hello"

        // Test case 3
        System.out.println("Test 3: \"" + sol.reverseWords("a good   example") + "\"");
        // Expected: "example good a"
    }
}
