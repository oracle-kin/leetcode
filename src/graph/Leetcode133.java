package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. Clone Graph
 * Difficulty: Medium
 * Tags: Hash Table, DFS, BFS, Graph
 * URL: https://leetcode.com/problems/clone-graph/
 *
 * Problem Description:
 * Return a deep copy (clone) of a connected undirected graph.
 */
public class Leetcode133 {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * Time: O(V + E)  — each node and edge visited once
     * Space: O(V)     — hash map + recursion stack
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);

        Node clone = new Node(node.val);
        map.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }
        return clone;
    }

    public static void main(String[] args) {
        Leetcode133 sol = new Leetcode133();

        // Test 1: Single node
        Node n1 = new Node(1);
        Node res1 = sol.cloneGraph(n1);
        System.out.println("Test 1: " + res1.val + " | Expected: 1");

        // Test 2: Two connected nodes
        Node a = new Node(1);
        Node b = new Node(2);
        a.neighbors.add(b);
        b.neighbors.add(a);
        Node res2 = sol.cloneGraph(a);
        System.out.println("Test 2: val=" + res2.val + ", neighbors=" + res2.neighbors.get(0).val + " | Expected: 1, neighbors: 2");

        // Test 3: null
        Node res3 = sol.cloneGraph(null);
        System.out.println("Test 3: " + res3 + " | Expected: null");
    }
}
