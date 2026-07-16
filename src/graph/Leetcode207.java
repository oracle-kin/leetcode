package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. Course Schedule
 * Difficulty: Medium
 * Tags: DFS, BFS, Graph, Topological Sort
 * URL: https://leetcode.com/problems/course-schedule/
 *
 * Problem Description:
 * Determine if you can finish all courses given prerequisites (detect cycle in directed graph).
 */
public class Leetcode207 {

    /**
     * Kahn's algorithm (BFS topological sort)
     * Time: O(V + E)  — building graph + processing each node/edge
     * Space: O(V + E) — adjacency list + indegree array + queue
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        int completed = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completed++;
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return completed == numCourses;
    }

    public static void main(String[] args) {
        Leetcode207 sol = new Leetcode207();

        // Test 1
        System.out.println("Test 1: " + sol.canFinish(2, new int[][] { { 1, 0 } }) + " | Expected: true");

        // Test 2
        System.out.println("Test 2: " + sol.canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }) + " | Expected: false");

        // Test 3
        System.out.println("Test 3: " + sol.canFinish(5, new int[][] { { 1, 0 }, { 2, 1 }, { 3, 2 }, { 4, 3 } }) + " | Expected: true");
    }
}
