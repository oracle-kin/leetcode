package twopointer;

/**
 * 141. Linked List Cycle
 * Difficulty: Easy
 * Tags: Hash Table, Linked List, Two Pointers
 * URL: https://leetcode.com/problems/linked-list-cycle/
 *
 * Problem Description:
 * Determine if a linked list has a cycle (Floyd's cycle detection / tortoise and hare).
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode141 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode141 sol = new Leetcode141();

        // Test case 1: cycle at index 1 (3 -> 2 -> 0 -> -4 -> 2)
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        System.out.println("Test 1 (has cycle): " + sol.hasCycle(n1));
        // Expected: true

        // Test case 2: no cycle
        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(2);
        n5.next = n6;
        System.out.println("Test 2 (no cycle): " + sol.hasCycle(n5));
        // Expected: false

        // Test case 3: single node no cycle
        System.out.println("Test 3 (single node): " + sol.hasCycle(new ListNode(1)));
        // Expected: false
    }
}
