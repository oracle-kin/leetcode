package backtracking;

import java.util.*;

/**
 * 126. Word Ladder II
 * Difficulty: Hard
 * Tags: Hash Table, String, Backtracking, BFS
 * URL: https://leetcode.com/problems/word-ladder-ii/
 *
 * Problem Description:
 * Return all shortest transformation sequences from beginWord to endWord.
 * Each transformed word must exist in wordList.
 *
 * Complexity: O(N * K^2) time for BFS building, where N = wordList size, K = word length.
 * Backtracking part depends on number of shortest paths.
 */
public class Leetcode126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return result;

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                int curDist = distance.get(word);

                List<String> neighbors = getNeighbors(word, wordSet);
                for (String neighbor : neighbors) {
                    adj.computeIfAbsent(word, k -> new ArrayList<>()).add(neighbor);

                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDist + 1);
                        if (neighbor.equals(endWord)) found = true;
                        else queue.offer(neighbor);
                    }
                }
            }
            if (found) break;
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(beginWord);
            backtrack(result, path, adj, beginWord, endWord, distance);
        }

        return result;
    }

    private void backtrack(List<List<String>> result, List<String> path, Map<String, List<String>> adj,
                           String current, String endWord, Map<String, Integer> distance) {
        if (current.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (!adj.containsKey(current)) return;

        for (String neighbor : adj.get(current)) {
            if (distance.get(neighbor) == distance.get(current) + 1) {
                path.add(neighbor);
                backtrack(result, path, adj, neighbor, endWord, distance);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) neighbors.add(newWord);
            }
            chars[i] = old;
        }
        return neighbors;
    }

    public static void main(String[] args) {
        Leetcode126 sol = new Leetcode126();

        // Test 1: beginWord="hit", endWord="cog", wordList=["hot","dot","dog","lot","log","cog"]
        // => [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("Test 1: " + sol.findLadders("hit", "cog", wordList));
    }
}
