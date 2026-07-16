package math;

/**
 * 149. Max Points on a Line
 * Difficulty: Hard
 * Tags: Array, Hash Table, Math, Geometry
 * URL: https://leetcode.com/problems/max-points-on-a-line/
 *
 * Problem Description:
 * Given an array of points on a 2D plane, return the maximum number of
 * points that lie on the same straight line.
 *
 * Complexity: O(n^2) time, O(n) space
 * Algorithm: For each point as a reference, compute the slope to every other
 * point. Use the slope (stored as a reduced fraction dx/dy via GCD) as a key
 * in a hashmap to count collinear points. Handle vertical lines (dx = 0) and
 * duplicate points separately.
 */
public class Leetcode149 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }

        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            // Use a hashmap of slope -> count, but since we can't use HashMap in pure
            // math-only approach easily, we can do a simpler O(n^3) check or
            // use the cross-product approach. Let's use the O(n^2) with java.util.HashMap.
            java.util.Map<String, Integer> slopeCount = new java.util.HashMap<>();
            int duplicates = 0;
            int currentMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                // Normalize sign: ensure dx is non-negative (or if dx==0, dy positive)
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0 && dy < 0) {
                    dy = -dy;
                }

                String slope = dy + "/" + dx;
                int count = slopeCount.getOrDefault(slope, 0) + 1;
                slopeCount.put(slope, count);
                currentMax = Math.max(currentMax, count);
            }

            maxCount = Math.max(maxCount, currentMax + duplicates + 1);
        }

        return maxCount;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static void main(String[] args) {
        Leetcode149 sol = new Leetcode149();

        // Test case 1
        int[][] points1 = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println("Test 1: " + sol.maxPoints(points1)); // Expected: 3

        // Test case 2
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println("Test 2: " + sol.maxPoints(points2)); // Expected: 4

        // Test case 3
        int[][] points3 = {{0, 0}};
        System.out.println("Test 3: " + sol.maxPoints(points3)); // Expected: 1

        // Test case 4
        int[][] points4 = {{0, 0}, {0, 0}};
        System.out.println("Test 4: " + sol.maxPoints(points4)); // Expected: 2

        // Test case 5
        int[][] points5 = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println("Test 5: " + sol.maxPoints(points5)); // Expected: 3
    }
}
