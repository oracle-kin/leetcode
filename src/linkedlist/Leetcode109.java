package linkedlist;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Difficulty: Medium
 * Tags: Linked List, Divide and Conquer, Tree, BST, Binary Tree
 * URL: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * Problem Description:
 * Convert a sorted singly linked list to a height-balanced BST.
 *
 * Complexity: O(N log N) time with find-middle, O(log N) space for recursion.
 * Can be optimized to O(N) time with inorder simulation, but this is the standard approach.
 */
public class Leetcode109 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null) prev.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }

    public static void main(String[] args) {
        Leetcode109 sol = new Leetcode109();

        // Test 1: [-10,-3,0,5,9] => balanced BST
        ListNode head = new ListNode(-10, new ListNode(-3, new ListNode(0, new ListNode(5, new ListNode(9)))));
        TreeNode root = sol.sortedListToBST(head);
        System.out.print("Test 1 (inorder): ");
        printInorder(root); // Expected: -10 -3 0 5 9
        System.out.println();

        // Test 2: [] => null
        root = sol.sortedListToBST(null);
        System.out.println("Test 2: " + (root == null ? "null" : "not null"));
    }

    static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}
