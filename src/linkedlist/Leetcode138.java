package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 * Difficulty: Medium
 * Tags: Hash Table, Linked List
 * URL: https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * Problem Description:
 * Make a deep copy of a linked list where each node has a next pointer and a random pointer.
 *
 * Complexity: O(N) time, O(1) space (interweaving approach, no hashmap).
 * Alternative hashmap approach: O(N) time, O(N) space.
 */
public class Leetcode138 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Interleave copied nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate lists
        curr = head;
        Node copyHead = head.next;
        Node copy = copyHead;
        while (curr != null) {
            curr.next = curr.next.next;
            if (copy.next != null) copy.next = copy.next.next;
            curr = curr.next;
            copy = copy.next;
        }

        return copyHead;
    }

    public static void main(String[] args) {
        Leetcode138 sol = new Leetcode138();

        // Test 1: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node head = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        head.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        head.random = null;
        n2.random = head;
        n3.random = n5;
        n4.random = n3;
        n5.random = head;

        Node copy = sol.copyRandomList(head);
        System.out.println("Test 1 (copy head val): " + copy.val); // 7
        System.out.println("  copy.next.val: " + copy.next.val); // 13
        System.out.println("  copy.random: " + (copy.random != null ? copy.random.val : "null")); // null
        System.out.println("  copy.next.random.val: " + copy.next.random.val); // 7
    }
}
