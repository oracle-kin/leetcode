package dp;

/**
 * 45. Jump Game II
 * Difficulty: Medium
 * Tags: Array, Dynamic Programming, Greedy
 * URL: https://leetcode.com/problems/jump-game-ii/
 * 
 * Problem Description:
 * Return the minimum number of jumps to reach the last index.
 */
public class Leetcode45 {
    /**
     * Greedy BFS | O(n) time, O(1) space
     * Track current jump's farthest reach. When we exhaust current range,
     * increment jumps and set next range.
     */
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < n - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        Leetcode45 sol = new Leetcode45();
        System.out.println(sol.jump(new int[]{2,3,1,1,4})); // 2
        System.out.println(sol.jump(new int[]{2,3,0,1,4})); // 2
        System.out.println(sol.jump(new int[]{0}));         // 0
        System.out.println(sol.jump(new int[]{1,2,3}));     // 2
    }
}
