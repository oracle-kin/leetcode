package dp;

/**
 * 42. Trapping Rain Water
 * Difficulty: Hard
 * Tags: Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack
 * URL: https://leetcode.com/problems/trapping-rain-water/
 * 
 * Problem Description:
 * Compute how much water it can trap after raining given an elevation map.
 */
public class Leetcode42 {
    /**
     * Two Pointers | O(n) time, O(1) space
     * Use left/right pointers and leftMax/rightMax.
     * If leftMax < rightMax, process left side: water += leftMax - height[left]
     * Otherwise process right side: water += rightMax - height[right]
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        Leetcode42 sol = new Leetcode42();
        System.out.println(sol.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
        System.out.println(sol.trap(new int[]{4,2,0,3,2,5})); // 9
        System.out.println(sol.trap(new int[]{1,1})); // 0
        System.out.println(sol.trap(new int[]{5,4,1,2})); // 1
    }
}
