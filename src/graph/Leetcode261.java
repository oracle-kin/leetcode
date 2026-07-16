package graph;

/**
 * 261. Graph Valid Tree
 * Difficulty: Medium
 * Tags: DFS, BFS, Union Find, Graph
 * URL: https://leetcode.com/problems/graph-valid-tree/
 *
 * Problem Description:
 * Given n nodes and a list of undirected edges, check whether these edges make up a valid tree. (Premium)
 */
public class Leetcode261 {

    /**
     * Union-Find approach. A valid tree must have exactly n-1 edges and be fully connected (no cycles).
     * Time: O(n + e * alpha(n))  — iterating edges + union-find with path compression
     * Space: O(n)                — parent array
     */
    public boolean validTree(int n, int[][] edges) {
        // A tree with n nodes must have exactly n-1 edges
        if (edges.length != n - 1) return false;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int pu = find(parent, u);
            int pv = find(parent, v);
            if (pu == pv) return false; // cycle detected
            parent[pu] = pv;
        }

        return true;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) {
        Leetcode261 sol = new Leetcode261();

        // Test 1: valid tree (star shape)
        System.out.println("Test 1: " + sol.validTree(5, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }) + " | Expected: true");

        // Test 2: cycle
        System.out.println("Test 2: " + sol.validTree(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } }) + " | Expected: false");

        // Test 3: disconnected components
        System.out.println("Test 3: " + sol.validTree(4, new int[][] { { 0, 1 }, { 2, 3 } }) + " | Expected: false");

        // Test 4: single node
        System.out.println("Test 4: " + sol.validTree(1, new int[][] {}) + " | Expected: true");
    }
}
