package linkedlist;

/**
 * 237. Delete Node in a Linked List
 * Difficulty: Medium
 * Tags: Linked List
 * URL: https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 * Problem Description:
 * Delete a given node from a singly linked list (not given head, only node to delete).
 * The node to delete is not the tail node.
 *
 * Complexity: O(1) time, O(1) space
 */
public class Leetcode237 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        Leetcode237 sol = new Leetcode237();

        // Test: [4,5,1,9], delete 5 => [4,1,9]
        ListNode head = new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(9))));
        System.out.print("Before: ");
        printList(head); // 4 5 1 9
        sol.deleteNode(head.next); // delete node with val 5
        System.out.print("After:  ");
        printList(head); // Expected: 4 1 9
    }

    static void printList(ListNode head) {
        if (head == null) { System.out.println("null"); return; }
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
