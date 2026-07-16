package twopointer;

/**
 * 82. Remove Duplicates from Sorted List II
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers
 * URL: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * Problem Description:
 * Remove all nodes that have duplicate numbers, leaving only distinct numbers.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode82 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null) {
            // Skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                prev.next = head.next; // skip entire duplicate group
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
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
        Leetcode82 sol = new Leetcode82();

        // Test case 1
        ListNode head1 = buildList(1, 2, 3, 3, 4, 4, 5);
        System.out.print("Test 1: ");
        printList(sol.deleteDuplicates(head1));
        // Expected: 1 2 5

        // Test case 2
        ListNode head2 = buildList(1, 1, 1, 2, 3);
        System.out.print("Test 2: ");
        printList(sol.deleteDuplicates(head2));
        // Expected: 2 3
    }
}
