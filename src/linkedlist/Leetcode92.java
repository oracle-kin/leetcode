package linkedlist;

/**
 * 92. Reverse Linked List II
 * Difficulty: Medium
 * Tags: Linked List
 * URL: https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Problem Description:
 * Reverse the nodes of a linked list from position left to right and return its modified list.
 *
 * Complexity: O(N) time, O(1) space
 */
public class Leetcode92 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) prev = prev.next;
        ListNode curr = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode92 sol = new Leetcode92();

        // Test 1: [1,2,3,4,5], left=2, right=4 => [1,4,3,2,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = sol.reverseBetween(head, 2, 4);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 4 3 2 5

        // Test 2: [5], left=1, right=1 => [5]
        res = sol.reverseBetween(new ListNode(5), 1, 1);
        System.out.print("Test 2: ");
        printList(res); // Expected: 5
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
