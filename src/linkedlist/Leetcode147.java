package linkedlist;

/**
 * 147. Insertion Sort List
 * Difficulty: Medium
 * Tags: Linked List, Sorting
 * URL: https://leetcode.com/problems/insertion-sort-list/
 *
 * Problem Description:
 * Sort a linked list using insertion sort.
 *
 * Complexity: O(N^2) time, O(1) space
 */
public class Leetcode147 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            ListNode next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode147 sol = new Leetcode147();

        // Test 1: [4,2,1,3] => [1,2,3,4]
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode res = sol.insertionSortList(head);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 2 3 4

        // Test 2: [-1,5,3,4,0] => [-1,0,3,4,5]
        head = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        res = sol.insertionSortList(head);
        System.out.print("Test 2: ");
        printList(res); // Expected: -1 0 3 4 5
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
