package tree;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * Difficulty: Easy
 * Tags: Array, Divide and Conquer, Tree, BST, Binary Tree
 * URL: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Problem Description:
 * Convert a sorted array to a height-balanced BST.
 */
public class Leetcode108 {

    // Time: O(n) | Space: O(log n) — recursively pick mid as root for height-balanced BST
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left  = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
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
        Leetcode108 sol = new Leetcode108();

        // Test: [-10,-3,0,5,9] → a height-balanced BST
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sol.sortedArrayToBST(nums);
        // inorder of the result should be [-10, -3, 0, 5, 9]
        System.out.println(root.val);           // 0 (or similar balanced root)
        System.out.println(root.left.val);      // -10 or -3
        System.out.println(root.right.val);     // 5 or 9
    }
}
