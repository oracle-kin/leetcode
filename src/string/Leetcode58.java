package string;

/**
 * 58. Length of Last Word
 * Difficulty: Easy
 * Tags: String
 * URL: https://leetcode.com/problems/length-of-last-word/
 *
 * Problem Description:
 * Return the length of the last word in a string.
 */
public class Leetcode58 {

    /**
     * Traverse from end: skip trailing spaces, count last word length.
     * Time: O(n)  — single pass from end
     * Space: O(1) — only primitives
     */
    public int lengthOfLastWord(String s) {
        int len = 0;
        int i = s.length() - 1;

        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') i--;

        // Count last word
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }

        return len;
    }

    public static void main(String[] args) {
        Leetcode58 sol = new Leetcode58();

        // Test 1
        System.out.println("Test 1: " + sol.lengthOfLastWord("Hello World") + " | Expected: 5");

        // Test 2
        System.out.println("Test 2: " + sol.lengthOfLastWord("   fly me   to   the moon  ") + " | Expected: 4");

        // Test 3
        System.out.println("Test 3: " + sol.lengthOfLastWord("luffy is still joyboy") + " | Expected: 6");
    }
}
