package dp;

import java.util.*;

/**
 * 140. Word Break II
 * Difficulty: Hard
 * Tags: Array, Hash Table, String, Dynamic Programming, Backtracking, Trie, Memoization
 * URL: https://leetcode.com/problems/word-break-ii/
 *
 * Problem Description:
 * Return all possible sentences of dictionary words that can be formed from the string.
 */
public class Leetcode140 {
    /**
     * DP + Memoized DFS: dp[i] = list of sentences for suffix s[i..n-1].
     * Use memoization to avoid recomputation.
     * Time: O(2^n) worst-case output size, Space: O(n * 2^n) for memo
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, dict, memo);
    }

    private List<String> dfs(String s, int start, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) return memo.get(start);
        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add("");
            return result;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (dict.contains(word)) {
                List<String> suffixes = dfs(s, end, dict, memo);
                for (String suffix : suffixes) {
                    result.add(word + (suffix.isEmpty() ? "" : " " + suffix));
                }
            }
        }
        memo.put(start, result);
        return result;
    }

    public static void main(String[] args) {
        Leetcode140 sol = new Leetcode140();
        System.out.println(sol.wordBreak("catsanddog",
            Arrays.asList("cat", "cats", "and", "sand", "dog")));
        // Expected: ["cats and dog", "cat sand dog"]
        System.out.println(sol.wordBreak("pineapplepenapple",
            Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        // Expected: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
        System.out.println(sol.wordBreak("catsandog",
            Arrays.asList("cats", "dog", "sand", "and", "cat")));
        // Expected: []
    }
}
