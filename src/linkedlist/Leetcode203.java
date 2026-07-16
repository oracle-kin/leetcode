package linkedlist;

/**
 * 203. Remove Linked List Elements
 * Difficulty: Easy
 * Tags: Linked List, Recursion
 * URL: https://leetcode.com/problems/remove-linked-list-elements/
 *
 * Problem Description:
 * Remove all elements from a linked list that have a specific value.
 *
 * Complexity: O(N) time, O(1) space
 */
public class Leetcode203 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode203 sol = new Leetcode203();

        // Test 1: [1,2,6,3,4,5,6], val=6 => [1,2,3,4,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        ListNode res = sol.removeElements(head, 6);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 2 3 4 5

        // Test 2: [], val=1 => null
        res = sol.removeElements(null, 1);
        System.out.print("Test 2: ");
        printList(res); // Expected: null

        // Test 3: [7,7,7,7], val=7 => null
        head = new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7))));
        res = sol.removeElements(head, 7);
        System.out.print("Test 3: ");
        printList(res); // Expected: null
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
