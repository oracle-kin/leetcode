package dp;

import java.util.*;

/**
 * 139. Word Break
 * Difficulty: Medium
 * Tags: Array, Hash Table, String, Dynamic Programming, Trie, Memoization
 * URL: https://leetcode.com/problems/word-break/
 *
 * Problem Description:
 * Return true if a string can be segmented into space-separated sequence of dictionary words.
 */
public class Leetcode139 {
    /**
     * DP: dp[i] = true if s[0..i-1] can be segmented.
     * dp[i] = any(dp[j] && wordDict.contains(s.substring(j,i))) for j < i.
     * Time: O(n^2 * L) where L is max word length (with substring),
     * can optimize to O(n * maxWordLen) by limiting inner loop.
     * Space: O(n + dictionary size)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String w : wordDict) maxLen = Math.max(maxLen, w.length());
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Leetcode139 sol = new Leetcode139();
        System.out.println(sol.wordBreak("leetcode", Arrays.asList("leet", "code")));        // Expected: true
        System.out.println(sol.wordBreak("applepenapple", Arrays.asList("apple", "pen")));   // Expected: true
        System.out.println(sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); // Expected: false
    }
}
