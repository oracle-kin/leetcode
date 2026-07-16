package string;

/**
 * 38. Count and Say
 * Difficulty: Medium
 * Tags: String
 * URL: https://leetcode.com/problems/count-and-say/
 *
 * Problem Description:
 * The count-and-say sequence is a sequence of digit strings defined recursively.
 */
public class Leetcode38 {

    /**
     * Iteratively build each term from the previous using run-length encoding.
     * Time: O(n * L) — L is the length of the longest string in the sequence
     * Space: O(L)   — StringBuilders
     */
    public String countAndSay(int n) {
        if (n <= 0) return "";

        String result = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(result.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(result.charAt(result.length() - 1));
            result = sb.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode38 sol = new Leetcode38();

        // Test 1
        System.out.println("Test 1: \"" + sol.countAndSay(1) + "\" | Expected: \"1\"");

        // Test 2
        System.out.println("Test 2: \"" + sol.countAndSay(4) + "\" | Expected: \"1211\"");

        // Test 3
        System.out.println("Test 3: \"" + sol.countAndSay(5) + "\" | Expected: \"111221\"");
    }
}
