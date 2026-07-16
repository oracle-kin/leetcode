package dp;

/**
 * 55. Jump Game
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Greedy
 * URL: https://leetcode.com/problems/jump-game/
 *
 * Problem Description:
 * Determine if you can reach the last index of the array.
 */
public class Leetcode55 {
    /**
     * Greedy | O(n) time, O(1) space
     * Track the farthest reachable index. If at any point i > reachable, return false.
     * If reachable >= last index, return true.
     */
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
            if (reachable >= nums.length - 1) return true;
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode55 sol = new Leetcode55();
        System.out.println(sol.canJump(new int[]{2,3,1,1,4})); // true
        System.out.println(sol.canJump(new int[]{3,2,1,0,4})); // false
        System.out.println(sol.canJump(new int[]{0}));        // true
        System.out.println(sol.canJump(new int[]{1,2}));       // true
    }
}
