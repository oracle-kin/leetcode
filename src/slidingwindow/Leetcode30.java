package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 * Difficulty: Hard
 * Tags: Hash Table, String, Sliding Window
 * URL: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * Problem Description:
 * Find all starting indices of substring(s) that is a concatenation of each word exactly once.
 */
public class Leetcode30 {

    /**
     * Sliding window over word-length steps. For each possible offset, maintain
     * a window of words_count words and check if the window matches the target count.
     * Time: O(n * w_len) — n is string length, w_len is word length
     * Space: O(m)        — m is number of words
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return result;

        Map<String, Integer> target = new HashMap<>();
        for (String w : words) {
            target.put(w, target.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int right = i; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);
                if (target.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;
                    while (seen.get(word) > target.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode30 sol = new Leetcode30();

        // Test 1
        System.out.println("Test 1: " + sol.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }) + " | Expected: [0, 9]");

        // Test 2
        System.out.println("Test 2: " + sol.findSubstring("wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" }) + " | Expected: []");

        // Test 3
        System.out.println("Test 3: " + sol.findSubstring("barfoofoobarthefoobarman", new String[] { "bar", "foo", "the" }) + " | Expected: [6, 9, 12]");
    }
}
