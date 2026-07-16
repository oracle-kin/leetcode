package linkedlist;

/**
 * 21. Merge Two Sorted Lists
 * Difficulty: Easy
 * Tags: Linked List, Recursion
 * URL: https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * Problem Description:
 * Merge two sorted linked lists and return it as a sorted list.
 *
 * Complexity: O(N + M) time, O(1) space (iterative)
 */
public class Leetcode21 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode21 sol = new Leetcode21();

        // Test 1: [1,2,4] + [1,3,4] => [1,1,2,3,4,4]
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode res = sol.mergeTwoLists(l1, l2);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 1 2 3 4 4

        // Test 2: [] + [] => []
        res = sol.mergeTwoLists(null, null);
        System.out.print("Test 2: ");
        printList(res); // Expected: (empty)

        // Test 3: [] + [0] => [0]
        res = sol.mergeTwoLists(null, new ListNode(0));
        System.out.print("Test 3: ");
        printList(res); // Expected: 0
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
