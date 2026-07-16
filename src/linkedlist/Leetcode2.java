package linkedlist;

/**
 * 2. Add Two Numbers
 * Difficulty: Medium
 * Tags: Linked List, Math
 * URL: https://leetcode.com/problems/add-two-numbers/
 *
 * Problem Description:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order. Add the two numbers and return the sum as a linked list.
 *
 * Complexity: O(max(N, M)) time, O(max(N, M)) space (for output)
 */
public class Leetcode2 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode2 sol = new Leetcode2();

        // Test 1: 342 + 465 = 807  =>  2->4->3 + 5->6->4 = 7->0->8
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode res = sol.addTwoNumbers(l1, l2);
        System.out.print("Test 1: ");
        printList(res); // Expected: 7 0 8

        // Test 2: 0 + 0 = 0
        l1 = new ListNode(0);
        l2 = new ListNode(0);
        res = sol.addTwoNumbers(l1, l2);
        System.out.print("Test 2: ");
        printList(res); // Expected: 0

        // Test 3: 999 + 1 = 1000  =>  9->9->9 + 1 = 0->0->0->1
        l1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        l2 = new ListNode(1);
        res = sol.addTwoNumbers(l1, l2);
        System.out.print("Test 3: ");
        printList(res); // Expected: 0 0 0 1
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
