package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 210. Course Schedule II
 * Difficulty: Medium
 * Tags: DFS, BFS, Graph, Topological Sort
 * URL: https://leetcode.com/problems/course-schedule-ii/
 *
 * Problem Description:
 * Return the ordering of courses you should take to finish all courses (topological sort).
 */
public class Leetcode210 {

    /**
     * Kahn's algorithm (BFS topological sort)
     * Time: O(V + E)  — building graph + processing each node/edge
     * Space: O(V + E) — adjacency list + indegree array + queue + result
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            int course = pre[0], prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[idx++] = curr;
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return idx == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        Leetcode210 sol = new Leetcode210();

        // Test 1
        System.out.println("Test 1: " + Arrays.toString(sol.findOrder(2, new int[][] { { 1, 0 } })) + " | Expected: [0, 1]");

        // Test 2
        System.out.println("Test 2: " + Arrays.toString(sol.findOrder(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } })) + " | Expected: [0, 1, 2, 3] or [0, 2, 1, 3]");

        // Test 3 (cycle)
        System.out.println("Test 3: " + Arrays.toString(sol.findOrder(2, new int[][] { { 0, 1 }, { 1, 0 } })) + " | Expected: []");
    }
}
