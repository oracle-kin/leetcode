package twopointer;

/**
 * 86. Partition List
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers
 * URL: https://leetcode.com/problems/partition-list/
 *
 * Problem Description:
 * Partition a linked list such that all nodes less than x come before nodes greater than or equal to x.
 * Preserve the original relative order in each partition.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode86 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode less = lessDummy, greater = greaterDummy;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        greater.next = null; // terminate the greater list
        less.next = greaterDummy.next; // connect two lists
        return lessDummy.next;
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
        Leetcode86 sol = new Leetcode86();

        // Test case 1
        ListNode head1 = buildList(1, 4, 3, 2, 5, 2);
        System.out.print("Test 1: ");
        printList(sol.partition(head1, 3));
        // Expected: 1 2 2 4 3 5

        // Test case 2
        ListNode head2 = buildList(2, 1);
        System.out.print("Test 2: ");
        printList(sol.partition(head2, 2));
        // Expected: 1 2
    }
}
