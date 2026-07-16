package greedy;

/**
 * 11. Container With Most Water
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Greedy
 * URL: https://leetcode.com/problems/container-with-most-water/
 *
 * Problem Description:
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 */
public class Leetcode11 {

    /**
     * Two-pointer approach: move the shorter line inward.
     * Time: O(n)  — single pass
     * Space: O(1) — only pointers and max variable
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            maxArea = Math.max(maxArea, h * w);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Leetcode11 sol = new Leetcode11();

        // Test 1
        System.out.println("Test 1: " + sol.maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }) + " | Expected: 49");

        // Test 2
        System.out.println("Test 2: " + sol.maxArea(new int[] { 1, 1 }) + " | Expected: 1");

        // Test 3
        System.out.println("Test 3: " + sol.maxArea(new int[] { 4, 3, 2, 1, 4 }) + " | Expected: 16");
    }
}
