package linkedlist;

/**
 * 206. Reverse Linked List
 * Difficulty: Easy
 * Tags: Linked List, Recursion
 * URL: https://leetcode.com/problems/reverse-linked-list/
 *
 * Problem Description:
 * Reverse a singly linked list (iteratively and recursively).
 *
 * Complexity: O(N) time, O(1) space (iterative), O(N) space (recursive)
 */
public class Leetcode206 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Iterative approach
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // Recursive approach
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Leetcode206 sol = new Leetcode206();

        // Test 1: [1,2,3,4,5] => [5,4,3,2,1]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = sol.reverseList(head);
        System.out.print("Test 1 (iterative): ");
        printList(res); // Expected: 5 4 3 2 1

        // Test 2: [1,2] => [2,1] (recursive)
        head = new ListNode(1, new ListNode(2));
        res = sol.reverseListRecursive(head);
        System.out.print("Test 2 (recursive): ");
        printList(res); // Expected: 2 1
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
