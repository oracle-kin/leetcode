package tree;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Difficulty: Medium
 * Tags: Tree, DFS, BFS, BST, Binary Tree
 * URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Problem Description:
 * Find the lowest common ancestor (LCA) of two nodes in a BST.
 */
public class Leetcode235 {

    // Time: O(h) | Space: O(1) — iterative: walk down the BST using the BST property
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val)
                cur = cur.left;
            else if (p.val > cur.val && q.val > cur.val)
                cur = cur.right;
            else
                return cur; // split point = LCA
        }
        return null;
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
        // Test 1: [6,2,8,0,4,7,9,null,null,3,5], p=2, q=8 → 6
        TreeNode r1 = new TreeNode(6,
                new TreeNode(2, new TreeNode(0),
                        new TreeNode(4, new TreeNode(3), new TreeNode(5))),
                new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        Leetcode235 sol = new Leetcode235();
        System.out.println(sol.lowestCommonAncestor(r1, r1.left, r1.right).val); // 6

        // Test 2: same tree, p=2, q=4 → 2
        System.out.println(sol.lowestCommonAncestor(r1, r1.left, r1.left.right).val); // 2
    }
}
