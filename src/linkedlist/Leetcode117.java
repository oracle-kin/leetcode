package linkedlist;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * Difficulty: Medium
 * Tags: Linked List, Tree, DFS, BFS, Binary Tree
 * URL: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * Problem Description:
 * Same as 116 but for any binary tree (not necessarily perfect).
 *
 * Complexity: O(N) time, O(1) space
 */
public class Leetcode117 {

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
        Node head = root;

        while (head != null) {
            Node dummy = new Node(0);
            Node curr = dummy;

            while (head != null) {
                if (head.left != null) {
                    curr.next = head.left;
                    curr = curr.next;
                }
                if (head.right != null) {
                    curr.next = head.right;
                    curr = curr.next;
                }
                head = head.next;
            }

            head = dummy.next;
        }

        return root;
    }

    public static void main(String[] args) {
        Leetcode117 sol = new Leetcode117();

        // Test 1: [1,2,3,4,5,null,7]
        Node root = new Node(1,
            new Node(2, new Node(4), new Node(5), null),
            new Node(3, null, new Node(7), null),
            null
        );
        sol.connect(root);
        System.out.println("Test 1:");
        System.out.println("  2.next = " + (root.left.next != null ? root.left.next.val : "null")); // 3
        System.out.println("  4.next = " + (root.left.left.next != null ? root.left.left.next.val : "null")); // 5
        System.out.println("  5.next = " + (root.left.right.next != null ? root.left.right.next.val : "null")); // 7
    }
}
