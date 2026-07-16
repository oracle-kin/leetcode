package string;

/**
 * 6. Zigzag Conversion
 * Difficulty: Medium
 * Tags: String
 * URL: https://leetcode.com/problems/zigzag-conversion/
 *
 * Problem Description:
 * Convert a string to a zigzag pattern on a given number of rows and read line by line.
 */
public class Leetcode6 {

    /**
     * Simulate row traversal. Use a flag to change direction when hitting top/bottom row.
     * Time: O(n)  — single pass through string
     * Space: O(n) — StringBuilders for each row + result
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int row = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0 || row == numRows - 1) {
                goingDown = !goingDown;
            }
            row += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rows) {
            result.append(sb);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Leetcode6 sol = new Leetcode6();

        // Test 1
        System.out.println("Test 1: \"" + sol.convert("PAYPALISHIRING", 3) + "\" | Expected: \"PAHNAPLSIIGYIR\"");

        // Test 2
        System.out.println("Test 2: \"" + sol.convert("PAYPALISHIRING", 4) + "\" | Expected: \"PINALSIGYAHRPI\"");

        // Test 3
        System.out.println("Test 3: \"" + sol.convert("A", 1) + "\" | Expected: \"A\"");
    }
}
