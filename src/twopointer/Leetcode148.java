package twopointer;

/**
 * 148. Sort List
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers, Divide and Conquer, Sorting, Merge Sort
 * URL: https://leetcode.com/problems/sort-list/
 *
 * Problem Description:
 * Sort a linked list in O(n log n) time and O(1) space.
 *
 * Time: O(n log n) | Space: O(log n) recursion stack (or O(1) with iterative merge)
 */
public class Leetcode148 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: split list into two halves (slow/fast pointer)
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // break list

        // Step 2: recursively sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // Step 3: merge sorted halves
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
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
        Leetcode148 sol = new Leetcode148();

        // Test case 1
        ListNode head1 = buildList(4, 2, 1, 3);
        System.out.print("Test 1: ");
        printList(sol.sortList(head1));
        // Expected: 1 2 3 4

        // Test case 2
        ListNode head2 = buildList(-1, 5, 3, 4, 0);
        System.out.print("Test 2: ");
        printList(sol.sortList(head2));
        // Expected: -1 0 3 4 5
    }
}
