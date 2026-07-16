package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * Difficulty: Medium
 * Tags: Hash Table, Linked List, Design, Doubly-Linked List
 * URL: https://leetcode.com/problems/lru-cache/
 *
 * Problem Description:
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * LRUCache(int capacity): initialize with positive capacity.
 * int get(int key): return value if key exists, else -1.
 * void put(int key, int value): update if key exists, else add. If capacity exceeded, evict LRU.
 *
 * Complexity: O(1) time for both get and put, O(capacity) space.
 */
public class Leetcode146 {

    class LRUCache {
        class Node {
            int key, val;
            Node prev, next;
            Node(int k, int v) { key = k; val = v; }
        }

        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            remove(node);
            addToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                remove(map.get(key));
            } else if (map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }

    public static void main(String[] args) {
        Leetcode146 leetcode = new Leetcode146();
        LRUCache cache = leetcode.new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Test 1 - get(1): " + cache.get(1)); // 1
        cache.put(3, 3); // evicts key 2
        System.out.println("Test 2 - get(2): " + cache.get(2)); // -1
        cache.put(4, 4); // evicts key 1
        System.out.println("Test 3 - get(1): " + cache.get(1)); // -1
        System.out.println("Test 4 - get(3): " + cache.get(3)); // 3
        System.out.println("Test 5 - get(4): " + cache.get(4)); // 4
    }
}
