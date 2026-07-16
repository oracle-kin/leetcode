package linkedlist;

/**
 * 24. Swap Nodes in Pairs
 * Difficulty: Medium
 * Tags: Linked List, Recursion
 * URL: https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Problem Description:
 * Swap every two adjacent nodes in a linked list and return its head.
 *
 * Complexity: O(N) time, O(1) space (iterative)
 */
public class Leetcode24 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;
            first.next = second.next;
            second.next = first;
            prev.next = second;
            prev = first;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode24 sol = new Leetcode24();

        // Test 1: [1,2,3,4] => [2,1,4,3]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode res = sol.swapPairs(head);
        System.out.print("Test 1: ");
        printList(res); // Expected: 2 1 4 3

        // Test 2: [] => null
        res = sol.swapPairs(null);
        System.out.print("Test 2: ");
        printList(res); // Expected: null

        // Test 3: [1] => [1]
        res = sol.swapPairs(new ListNode(1));
        System.out.print("Test 3: ");
        printList(res); // Expected: 1

        // Test 4: [1,2,3] => [2,1,3]
        head = new ListNode(1, new ListNode(2, new ListNode(3)));
        res = sol.swapPairs(head);
        System.out.print("Test 4: ");
        printList(res); // Expected: 2 1 3
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
