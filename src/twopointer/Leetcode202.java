package twopointer;

/**
 * 202. Happy Number
 * Difficulty: Easy
 * Tags: Hash Table, Math, Two Pointers
 * URL: https://leetcode.com/problems/happy-number/
 *
 * Problem Description:
 * Determine if a number is happy (repeatedly replacing with sum of squares of digits eventually reaches 1).
 * Uses Floyd's cycle detection to detect infinite loops.
 *
 * Time: O(log n) | Space: O(1)
 */
public class Leetcode202 {

    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Leetcode202 sol = new Leetcode202();

        // Test case 1
        System.out.println("Test 1: " + sol.isHappy(19));
        // Expected: true (1^2 + 9^2 = 82 -> 68 -> 100 -> 1)

        // Test case 2
        System.out.println("Test 2: " + sol.isHappy(2));
        // Expected: false (enters cycle: 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4)

        // Test case 3
        System.out.println("Test 3: " + sol.isHappy(7));
        // Expected: true
    }
}
