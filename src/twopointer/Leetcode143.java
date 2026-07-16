package twopointer;

/**
 * 143. Reorder List
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers, Stack, Recursion
 * URL: https://leetcode.com/problems/reorder-list/
 *
 * Problem Description:
 * Reorder the list to L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * Must be done in-place.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode143 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: find middle using slow/fast
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: reverse second half
        ListNode prev = null, curr = slow.next;
        slow.next = null; // break the list
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: merge two halves
        ListNode first = head, second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
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
        Leetcode143 sol = new Leetcode143();

        // Test case 1
        ListNode head1 = buildList(1, 2, 3, 4);
        sol.reorderList(head1);
        System.out.print("Test 1: ");
        printList(head1);
        // Expected: 1 4 2 3

        // Test case 2
        ListNode head2 = buildList(1, 2, 3, 4, 5);
        sol.reorderList(head2);
        System.out.print("Test 2: ");
        printList(head2);
        // Expected: 1 5 2 4 3
    }
}
