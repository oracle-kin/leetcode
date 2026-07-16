package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 * Difficulty: Medium
 * Tags: String, Backtracking
 * URL: https://leetcode.com/problems/restore-ip-addresses/
 *
 * Problem Description:
 * Return all possible valid IP addresses that can be formed by inserting dots
 * into a string of digits.
 *
 * Complexity: O(1) time (max 3^4 = 81 combinations), O(1) space.
 */
public class Leetcode93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), s, 0, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, String s, int idx, int parts) {
        if (parts == 4 && idx == s.length()) {
            result.add(sb.substring(0, sb.length() - 1)); // remove trailing dot
            return;
        }
        if (parts == 4 || idx == s.length()) return;

        for (int len = 1; len <= 3 && idx + len <= s.length(); len++) {
            String segment = s.substring(idx, idx + len);
            if (segment.length() > 1 && segment.charAt(0) == '0') continue; // no leading zeros
            if (Integer.parseInt(segment) > 255) continue;

            int sbLen = sb.length();
            sb.append(segment).append('.');
            backtrack(result, sb, s, idx + len, parts + 1);
            sb.setLength(sbLen);
        }
    }

    public static void main(String[] args) {
        Leetcode93 sol = new Leetcode93();

        // Test 1: "25525511135" => ["255.255.11.135","255.255.111.35"]
        System.out.println("Test 1: " + sol.restoreIpAddresses("25525511135"));

        // Test 2: "0000" => ["0.0.0.0"]
        System.out.println("Test 2: " + sol.restoreIpAddresses("0000"));

        // Test 3: "101023" => ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
        System.out.println("Test 3: " + sol.restoreIpAddresses("101023"));
    }
}
