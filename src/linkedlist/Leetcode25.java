package linkedlist;

/**
 * 25. Reverse Nodes in k-Group
 * Difficulty: Hard
 * Tags: Linked List, Recursion
 * URL: https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * Problem Description:
 * Reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k, left-over nodes remain as-is.
 *
 * Complexity: O(N) time, O(1) space
 */
public class Leetcode25 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            while (curr != groupNext) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }

        return dummy.next;
    }

    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    public static void main(String[] args) {
        Leetcode25 sol = new Leetcode25();

        // Test 1: [1,2,3,4,5], k=2 => [2,1,4,3,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res = sol.reverseKGroup(head, 2);
        System.out.print("Test 1: ");
        printList(res); // Expected: 2 1 4 3 5

        // Test 2: [1,2,3,4,5], k=3 => [3,2,1,4,5]
        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        res = sol.reverseKGroup(head, 3);
        System.out.print("Test 2: ");
        printList(res); // Expected: 3 2 1 4 5
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
