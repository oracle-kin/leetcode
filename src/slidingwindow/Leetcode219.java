package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 219. Contains Duplicate II
 * Difficulty: Easy
 * Tags: Array, Hash Table, Sliding Window
 * URL: https://leetcode.com/problems/contains-duplicate-ii/
 *
 * Problem Description:
 * Check if there are two distinct indices i and j with nums[i]==nums[j] and abs(i-j)<=k.
 */
public class Leetcode219 {

    /**
     * Sliding window with HashSet. Maintain a window of size k.
     * Time: O(n)  — each element added/removed once
     * Space: O(k) — hash set of at most k elements
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Leetcode219 sol = new Leetcode219();

        // Test 1
        System.out.println("Test 1: " + sol.containsNearbyDuplicate(new int[] { 1, 2, 3, 1 }, 3) + " | Expected: true");

        // Test 2
        System.out.println("Test 2: " + sol.containsNearbyDuplicate(new int[] { 1, 0, 1, 1 }, 1) + " | Expected: true");

        // Test 3
        System.out.println("Test 3: " + sol.containsNearbyDuplicate(new int[] { 1, 2, 3, 1, 2, 3 }, 2) + " | Expected: false");
    }
}
