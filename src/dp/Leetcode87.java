package dp;

/**
 * 87. Scramble String
 * Difficulty: Hard
 * Tags: String, Dynamic Programming
 * URL: https://leetcode.com/problems/scramble-string/
 *
 * Problem Description:
 * Determine if one string is a scramble of another.
 */
public class Leetcode87 {
    /**
     * Recursive with Memoization | O(n^4) time, O(n^3) space
     * Check all possible split points. Two cases:
     * 1. s1[0..len) scrambled to s2[0..len) without swap
     * 2. s1[0..len) scrambled to s2[0..len) with swap of subtrees
     * Use a 3D memo to cache results for (i1, i2, len).
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        Boolean[][][] memo = new Boolean[n][n][n + 1];
        return scrambleCheck(s1, s2, 0, 0, n, memo);
    }

    private boolean scrambleCheck(String s1, String s2, int i1, int i2, int len, Boolean[][][] memo) {
        if (memo[i1][i2][len] != null) return memo[i1][i2][len];
        if (s1.substring(i1, i1 + len).equals(s2.substring(i2, i2 + len))) {
            memo[i1][i2][len] = true;
            return true;
        }
        int[] count = new int[26];
        for (int k = 0; k < len; k++) {
            count[s1.charAt(i1 + k) - 'a']++;
            count[s2.charAt(i2 + k) - 'a']--;
        }
        for (int k = 0; k < 26; k++) {
            if (count[k] != 0) {
                memo[i1][i2][len] = false;
                return false;
            }
        }
        for (int k = 1; k < len; k++) {
            if (scrambleCheck(s1, s2, i1, i2, k, memo) &&
                scrambleCheck(s1, s2, i1 + k, i2 + k, len - k, memo)) {
                memo[i1][i2][len] = true;
                return true;
            }
            if (scrambleCheck(s1, s2, i1, i2 + len - k, k, memo) &&
                scrambleCheck(s1, s2, i1 + k, i2, len - k, memo)) {
                memo[i1][i2][len] = true;
                return true;
            }
        }
        memo[i1][i2][len] = false;
        return false;
    }

    public static void main(String[] args) {
        Leetcode87 sol = new Leetcode87();
        System.out.println(sol.isScramble("great", "rgeat"));   // true
        System.out.println(sol.isScramble("abcde", "caebd"));   // false
        System.out.println(sol.isScramble("a", "a"));           // true
        System.out.println(sol.isScramble("abb", "bba"));       // true
    }
}
