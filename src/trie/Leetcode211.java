package trie;

/**
 * 211. Design Add and Search Words Data Structure
 * Difficulty: Medium
 * Tags: String, DFS, Design, Trie
 * URL: https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * Problem Description:
 * Design a data structure that supports adding words and searching with wildcard '.'.
 */
public class Leetcode211 {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private TrieNode root;

    public Leetcode211() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     * Time: O(L)  — L is word length
     * Space: O(L)
     */
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    /**
     * Returns true if the word is in the data structure. '.' matches any letter.
     * Time: O(26^L) worst case, O(L) average for words without wildcards
     * Space: O(L) — recursion stack depth
     */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (index == word.length()) return node.isEnd;

        char c = word.charAt(index);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && dfs(word, index + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            int idx = c - 'a';
            if (node.children[idx] == null) return false;
            return dfs(word, index + 1, node.children[idx]);
        }
    }

    public static void main(String[] args) {
        Leetcode211 dict = new Leetcode211();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println("Test 1 (search pad): " + dict.search("pad") + " | Expected: false");
        System.out.println("Test 2 (search bad): " + dict.search("bad") + " | Expected: true");
        System.out.println("Test 3 (search .ad): " + dict.search(".ad") + " | Expected: true");
        System.out.println("Test 4 (search b..): " + dict.search("b..") + " | Expected: true");
    }
}
