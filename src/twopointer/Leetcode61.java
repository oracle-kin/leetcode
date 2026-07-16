package twopointer;

/**
 * 61. Rotate List
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers
 * URL: https://leetcode.com/problems/rotate-list/
 *
 * Problem Description:
 * Rotate a linked list to the right by k places.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode61 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Find length and tail
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // Make it circular
        tail.next = head;

        // Find the new tail: (len - k % len - 1) steps from head
        k = k % len;
        int stepsToNewTail = len - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    private static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Leetcode61 sol = new Leetcode61();

        // Test case 1
        ListNode head1 = buildList(1, 2, 3, 4, 5);
        System.out.print("Test 1: ");
        printList(sol.rotateRight(head1, 2));
        // Expected: 4 5 1 2 3

        // Test case 2
        ListNode head2 = buildList(0, 1, 2);
        System.out.print("Test 2: ");
        printList(sol.rotateRight(head2, 4));
        // Expected: 2 0 1 (4 % 3 = 1 rotation)
    }
}
