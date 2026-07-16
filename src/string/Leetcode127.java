package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 127. Word Ladder
 * Difficulty: Hard
 * Tags: Hash Table, String, BFS
 * URL: https://leetcode.com/problems/word-ladder/
 *
 * Problem Description:
 * Return the length of the shortest transformation sequence from beginWord to endWord.
 */
public class Leetcode127 {

    /**
     * BFS from beginWord. For each word, try all 26 letters at each position.
     * Time: O(n * L * 26) — n words, L length of each word
     * Space: O(n)         — queue + set
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return level;

                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String next = new String(chars);
                        if (wordSet.contains(next)) {
                            queue.offer(next);
                            wordSet.remove(next);
                        }
                    }
                    chars[j] = original;
                }
            }
            level++;
        }

        return 0;
    }

    public static void main(String[] args) {
        Leetcode127 sol = new Leetcode127();

        // Test 1
        List<String> list1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println("Test 1: " + sol.ladderLength("hit", "cog", list1) + " | Expected: 5");

        // Test 2
        List<String> list2 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        System.out.println("Test 2: " + sol.ladderLength("hit", "cog", list2) + " | Expected: 0");

        // Test 3
        List<String> list3 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println("Test 3: " + sol.ladderLength("a", "c", list3) + " | Expected: 2");
    }
}
