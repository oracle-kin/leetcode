package linkedlist;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * Difficulty: Hard
 * Tags: Linked List, Divide and Conquer, Heap
 * URL: https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Problem Description:
 * Merge k sorted linked lists and return it as one sorted list.
 *
 * Complexity: O(N log K) time using min-heap, where N = total nodes, K = number of lists. O(K) space for heap.
 */
public class Leetcode23 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) minHeap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            curr.next = min;
            curr = curr.next;
            if (min.next != null) minHeap.offer(min.next);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Leetcode23 sol = new Leetcode23();

        // Test 1: [[1,4,5],[1,3,4],[2,6]] => [1,1,2,3,4,4,5,6]
        ListNode[] lists = new ListNode[]{
            new ListNode(1, new ListNode(4, new ListNode(5))),
            new ListNode(1, new ListNode(3, new ListNode(4))),
            new ListNode(2, new ListNode(6))
        };
        ListNode res = sol.mergeKLists(lists);
        System.out.print("Test 1: ");
        printList(res); // Expected: 1 1 2 3 4 4 5 6

        // Test 2: [] => null
        res = sol.mergeKLists(new ListNode[]{});
        System.out.print("Test 2: ");
        printList(res); // Expected: null

        // Test 3: [[]] => null
        res = sol.mergeKLists(new ListNode[]{null});
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
