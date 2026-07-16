package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 269. Alien Dictionary
 * Difficulty: Hard
 * Tags: Array, String, DFS, BFS, Graph, Topological Sort
 * URL: https://leetcode.com/problems/alien-dictionary/
 *
 * Problem Description:
 * Return the order of characters in an alien language. (Premium)
 */
public class Leetcode269 {

    /**
     * Topological sort on characters using BFS (Kahn's algorithm).
     * Time: O(C)     — C is total length of all words (building graph) + O(V+E) topological sort
     * Space: O(1)    — at most 26 characters, so constant
     */
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Initialize all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new ArrayList<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Build graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            // Invalid case: w1 is prefix of w2 but longer
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            int minLen = Math.min(w1.length(), w2.length());
            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    adj.get(c1).add(c2);
                    indegree.put(c2, indegree.get(c2) + 1);
                    break;
                }
            }
        }

        // BFS topological sort
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) queue.offer(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            sb.append(curr);
            for (char next : adj.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) queue.offer(next);
            }
        }

        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        Leetcode269 sol = new Leetcode269();

        // Test 1
        System.out.println("Test 1: \"" + sol.alienOrder(new String[] { "wrt", "wrf", "er", "ett", "rftt" }) + "\" | Expected: \"wertf\"");

        // Test 2
        System.out.println("Test 2: \"" + sol.alienOrder(new String[] { "z", "x" }) + "\" | Expected: \"zx\"");

        // Test 3 (invalid cycle)
        System.out.println("Test 3: \"" + sol.alienOrder(new String[] { "z", "x", "z" }) + "\" | Expected: \"\"");
    }
}
