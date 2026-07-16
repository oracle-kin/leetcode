package trie;

/**
 * 208. Implement Trie (Prefix Tree)
 * Difficulty: Medium
 * Tags: Hash Table, String, Design, Trie
 * URL: https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Problem Description:
 * Implement a trie with insert, search, and startsWith methods.
 */
public class Leetcode208 {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private TrieNode root;

    public Leetcode208() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the trie.
     * Time: O(L)  — L is word length
     * Space: O(L) — new nodes created
     */
    public void insert(String word) {
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
     * Returns true if the word is in the trie.
     * Time: O(L)
     * Space: O(1)
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns true if any word in the trie starts with the given prefix.
     * Time: O(L)
     * Space: O(1)
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }

    public static void main(String[] args) {
        Leetcode208 trie = new Leetcode208();
        trie.insert("apple");
        System.out.println("Test 1 (search apple): " + trie.search("apple") + " | Expected: true");
        System.out.println("Test 2 (search app): " + trie.search("app") + " | Expected: false");
        System.out.println("Test 3 (startsWith app): " + trie.startsWith("app") + " | Expected: true");
        trie.insert("app");
        System.out.println("Test 4 (search app after insert): " + trie.search("app") + " | Expected: true");
    }
}
