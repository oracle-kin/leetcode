package twopointer;

/**
 * 19. Remove Nth Node From End of List
 * Difficulty: Medium
 * Tags: Linked List, Two Pointers
 * URL: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Problem Description:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode19 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        // Move fast n+1 steps ahead so slow stops just before the target
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    // Helper to build list
    private static ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // Helper to print list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Leetcode19 sol = new Leetcode19();

        // Test case 1: remove 2nd from end
        ListNode head1 = buildList(1, 2, 3, 4, 5);
        System.out.print("Test 1: ");
        printList(sol.removeNthFromEnd(head1, 2));
        // Expected: 1 2 3 5

        // Test case 2: remove only element
        ListNode head2 = buildList(1);
        System.out.print("Test 2: ");
        printList(sol.removeNthFromEnd(head2, 1));
        // Expected: (empty)

        // Test case 3: remove head
        ListNode head3 = buildList(1, 2);
        System.out.print("Test 3: ");
        printList(sol.removeNthFromEnd(head3, 2));
        // Expected: 2
    }
}
