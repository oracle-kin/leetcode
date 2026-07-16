package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * Difficulty: Hard
 * Tags: Array, String, Backtracking, Trie, Matrix
 * URL: https://leetcode.com/problems/word-search-ii/
 *
 * Problem Description:
 * Given a board and a list of words, return all words on the board.
 * Same as Word Search I but optimized using a Trie.
 *
 * Complexity: O(M * N * 4^L) worst case (L = max word length). Trie prunes significantly.
 */
public class Leetcode212 {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // prevent duplicate
        }

        board[i][j] = '#';
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        board[i][j] = c;
    }

    public static void main(String[] args) {
        Leetcode212 sol = new Leetcode212();

        // Test 1: board=[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
        // words=["oath","pea","eat","rain"] => ["oath","eat"]
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println("Test 1: " + sol.findWords(board, words));
    }
}
