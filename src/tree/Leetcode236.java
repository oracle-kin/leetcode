package tree;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Difficulty: Medium
 * Tags: Tree, DFS, Binary Tree
 * URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Problem Description:
 * Find the lowest common ancestor (LCA) of two nodes in a binary tree.
 */
public class Leetcode236 {

    // Time: O(n) | Space: O(h) — recursive DFS: return node if it matches p/q, else null
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left  = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; // split point
        return left != null ? left : right;
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
        // Build tree: [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode r1 = new TreeNode(3,
                new TreeNode(5, new TreeNode(6),
                        new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                new TreeNode(1, new TreeNode(0), new TreeNode(8)));
        Leetcode236 sol = new Leetcode236();

        // Test 1: p=5, q=1 → 3
        System.out.println(sol.lowestCommonAncestor(r1, r1.left, r1.right).val); // 3

        // Test 2: p=5, q=4 → 5
        System.out.println(sol.lowestCommonAncestor(r1, r1.left, r1.left.right.right).val); // 5
    }
}
