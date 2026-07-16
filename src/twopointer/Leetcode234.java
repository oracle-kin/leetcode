package twopointer;

/**
 * 234. Palindrome Linked List
 * Difficulty: Easy
 * Tags: Linked List, Two Pointers, Stack, Recursion
 * URL: https://leetcode.com/problems/palindrome-linked-list/
 *
 * Problem Description:
 * Check if a singly linked list is a palindrome.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode234 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: reverse second half
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Step 3: compare first half and reversed second half
        ListNode left = head, right = prev;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
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

    public static void main(String[] args) {
        Leetcode234 sol = new Leetcode234();

        // Test case 1
        System.out.println("Test 1: " + sol.isPalindrome(buildList(1, 2, 2, 1)));
        // Expected: true

        // Test case 2
        System.out.println("Test 2: " + sol.isPalindrome(buildList(1, 2)));
        // Expected: false

        // Test case 3: single node
        System.out.println("Test 3: " + sol.isPalindrome(buildList(1)));
        // Expected: true
    }
}
