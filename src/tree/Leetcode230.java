package tree;

/**
 * 230. Kth Smallest Element in a BST
 * Difficulty: Medium
 * Tags: Tree, DFS, BFS, BST, Binary Tree
 * URL: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Problem Description:
 * Find the kth smallest element in a BST.
 */
public class Leetcode230 {

    private int count = 0;
    private int result = 0;

    // Time: O(h + k) on average | Space: O(h) — inorder DFS stops at k-th node
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;
        inorder(node.left, k);
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        inorder(node.right, k);
    }

    // ── TreeNode ─────────────────────────────────
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // ── Test Cases ───────────────────────────────
    public static void main(String[] args) {
        Leetcode230 sol = new Leetcode230();

        // Test 1: [3,1,4,null,2], k=1 → 1
        TreeNode r1 = new TreeNode(3,
                new TreeNode(1, null, new TreeNode(2)),
                new TreeNode(4));
        System.out.println(sol.kthSmallest(r1, 1)); // 1

        // Test 2: [5,3,6,2,4,null,null,1], k=3 → 3
        TreeNode r2 = new TreeNode(5,
                new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
                new TreeNode(6));
        System.out.println(sol.kthSmallest(r2, 3)); // 3
    }
}
