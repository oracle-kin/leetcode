package linkedlist;

/**
 * 83. Remove Duplicates from Sorted List
 * Difficulty: Easy
 * Tags: Linked List
 * URL: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * Problem Description:
 * Remove duplicates from a sorted linked list, leaving only distinct values.
 *
 * Complexity: O(N) time, O(1) space
 */
public class Leetcode83 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Leetcode83 sol = new Leetcode83();

        // Test 1: [1,1,2] => [1,2]
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2)));
        ListNode res = sol.deleteDuplicates(head);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 2

        // Test 2: [1,1,2,3,3] => [1,2,3]
        head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        res = sol.deleteDuplicates(head);
        System.out.print("Test 2: ");
        printList(res); // Expected: 1 2 3
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
