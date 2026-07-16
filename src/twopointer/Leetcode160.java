package twopointer;

/**
 * 160. Intersection of Two Linked Lists
 * Difficulty: Easy
 * Tags: Hash Table, Linked List, Two Pointers
 * URL: https://leetcode.com/problems/intersection-of-two-linked-lists/
 *
 * Problem Description:
 * Find the node at which the intersection of two singly linked lists begins.
 *
 * Time: O(m + n) | Space: O(1)
 */
public class Leetcode160 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pa = headA, pb = headB;

        // Each pointer traverses both lists; they meet at intersection or null
        while (pa != pb) {
            pa = (pa == null) ? headB : pa.next;
            pb = (pb == null) ? headA : pb.next;
        }
        return pa;
    }

    public static void main(String[] args) {
        Leetcode160 sol = new Leetcode160();

        // Test case 1: intersection at node with val 8
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        ListNode result = sol.getIntersectionNode(headA, headB);
        System.out.println("Test 1 (intersection): " + (result != null ? result.val : "null"));
        // Expected: 8

        // Test case 2: no intersection
        ListNode headC = new ListNode(2);
        headC.next = new ListNode(6);
        headC.next.next = new ListNode(4);

        ListNode headD = new ListNode(1);
        headD.next = new ListNode(5);

        ListNode result2 = sol.getIntersectionNode(headC, headD);
        System.out.println("Test 2 (no intersection): " + (result2 != null ? result2.val : "null"));
        // Expected: null
    }
}
