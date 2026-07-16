package math;

/**
 * 204. Count Primes
 * Difficulty: Medium
 * Tags: Array, Math, Enumeration, Number Theory
 * URL: https://leetcode.com/problems/count-primes/
 *
 * Problem Description:
 * Given an integer n, return the number of prime numbers strictly less than n.
 *
 * Complexity: O(n log log n) time, O(n) space
 * Algorithm: Sieve of Eratosthenes. Create a boolean array marking numbers
 * as prime initially. For each prime p starting from 2, mark all multiples
 * of p (starting from p*p) as non-prime. Count remaining primes.
 */
public class Leetcode204 {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];
        // Initialize all numbers >= 2 as prime candidates
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // Sieve of Eratosthenes
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count primes
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Leetcode204 sol = new Leetcode204();

        // Test case 1
        System.out.println("Test 1: " + sol.countPrimes(10)); // Expected: 4 (2, 3, 5, 7)

        // Test case 2
        System.out.println("Test 2: " + sol.countPrimes(0)); // Expected: 0

        // Test case 3
        System.out.println("Test 3: " + sol.countPrimes(1)); // Expected: 0

        // Test case 4
        System.out.println("Test 4: " + sol.countPrimes(2)); // Expected: 0

        // Test case 5
        System.out.println("Test 5: " + sol.countPrimes(100)); // Expected: 25
    }
}
