package linkedlist;

/**
 * 116. Populating Next Right Pointers in Each Node
 * Difficulty: Medium
 * Tags: Linked List, Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * Problem Description:
 * Populate each next pointer to point to its next right node in a perfect binary tree.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Complexity: O(N) time, O(1) space (using established next pointers)
 */
public class Leetcode116 {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        public Node(int _val) { val = _val; }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val; left = _left; right = _right; next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }

        return root;
    }

    public static void main(String[] args) {
        Leetcode116 sol = new Leetcode116();

        // Test 1: [1,2,3,4,5,6,7]
        Node root = new Node(1,
            new Node(2, new Node(4), new Node(5), null),
            new Node(3, new Node(6), new Node(7), null),
            null
        );
        sol.connect(root);
        System.out.println("Test 1:");
        System.out.println("  4.next = " + (root.left.left.next != null ? root.left.left.next.val : "null")); // 5
        System.out.println("  5.next = " + (root.left.right.next != null ? root.left.right.next.val : "null")); // 6
        System.out.println("  6.next = " + (root.right.left.next != null ? root.right.left.next.val : "null")); // 7
        System.out.println("  7.next = " + (root.right.right.next != null ? root.right.right.next.val : "null")); // null
    }
}
