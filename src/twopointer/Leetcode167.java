package twopointer;

/**
 * 167. Two Sum II - Input Array Is Sorted
 * Difficulty: Medium
 * Tags: Array, Two Pointers, Binary Search
 * URL: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Problem Description:
 * Find two numbers in a sorted array such that they add up to a specific target.
 * Return 1-indexed indices.
 *
 * Time: O(n) | Space: O(1)
 */
public class Leetcode167 {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-indexed
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1}; // not found (per problem constraints, always found)
    }

    public static void main(String[] args) {
        Leetcode167 sol = new Leetcode167();

        // Test case 1
        int[] result1 = sol.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Test 1: [" + result1[0] + ", " + result1[1] + "]");
        // Expected: [1, 2]

        // Test case 2
        int[] result2 = sol.twoSum(new int[]{2, 3, 4}, 6);
        System.out.println("Test 2: [" + result2[0] + ", " + result2[1] + "]");
        // Expected: [1, 3]

        // Test case 3
        int[] result3 = sol.twoSum(new int[]{-1, 0}, -1);
        System.out.println("Test 3: [" + result3[0] + ", " + result3[1] + "]");
        // Expected: [1, 2]
    }
}
