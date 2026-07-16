package twopointer;

/**
 * 142. Linked List Cycle II
 * Difficulty: Medium
 * Tags: Hash Table, Linked List, Two Pointers
 * URL: https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Problem Description:
 * Return the node where the cycle begins. If no cycle, return null.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode142 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        // Phase 1: detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // Phase 2: find entry point
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Leetcode142 sol = new Leetcode142();

        // Test case 1: cycle starts at node with val 2
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        ListNode result1 = sol.detectCycle(n1);
        System.out.println("Test 1 (cycle entry): " + (result1 != null ? result1.val : "null"));
        // Expected: 2

        // Test case 2: cycle starts at head
        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(2);
        n5.next = n6; n6.next = n5;
        ListNode result2 = sol.detectCycle(n5);
        System.out.println("Test 2 (cycle entry): " + (result2 != null ? result2.val : "null"));
        // Expected: 1

        // Test case 3: no cycle
        ListNode n7 = new ListNode(1);
        ListNode result3 = sol.detectCycle(n7);
        System.out.println("Test 3 (no cycle): " + (result3 != null ? result3.val : "null"));
        // Expected: null
    }
}
