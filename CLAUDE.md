# CLAUDE.md - LeetCode Practice Project

## Project Overview
This project is used for daily LeetCode algorithm practice, primarily targeting technical interviews at tech companies with offices in Ireland (Google Dublin, Meta Dublin, Amazon/AWS Dublin, Stripe, HubSpot, Intercom, Workday, Microsoft Dublin, TikTok Dublin, etc.).

## Tech Stack
- **Language:** Java (JDK 21+, using unnamed class / implicit main method where convenient)
- **Build System:** IntelliJ IDEA project (no Maven/Gradle)
- **Test Framework:** JUnit 5 (add to classpath when needed)

## Project Structure
```
leetcode/
├── CLAUDE.md              # This file
├── src/                    # Source root
│   ├── Main.java           # Scratchpad for quick tests
│   ├── array/              # Arrays & Hashing (9 files)
│   ├── backtracking/       # Backtracking (17 files)
│   ├── binarysearch/       # Binary Search (12 files)
│   ├── bit/                # Bit Manipulation (6 files)
│   ├── dp/                 # Dynamic Programming (37 files)
│   ├── graph/              # Graphs, Topological Sort, Union Find (8 files)
│   ├── greedy/             # Greedy (3 files)
│   ├── heap/               # Heap / Priority Queue (1 file)
│   ├── interval/           # Intervals (3 files)
│   ├── linkedlist/         # Linked List (17 files)
│   ├── math/               # Math & Geometry (11 files)
│   ├── slidingwindow/      # Sliding Window (6 files)
│   ├── stack/              # Stack / Monotonic Stack (9 files)
│   ├── string/             # String (10 files)
│   ├── tree/               # Trees, BST (23 files)
│   ├── trie/               # Trie (3 files)
│   └── twopointer/         # Two Pointers (25 files)
└── .idea/                  # IntelliJ project config
```

## File Naming Convention
- Each LeetCode problem gets its own file: `src/<pattern>/Leetcode{Number}.java`
- Example: `src/array/Leetcode1.java` for "Two Sum"
- Example: `src/dp/Leetcode53.java` for "Maximum Subarray"
- The class name matches the filename exactly
- Each file has a `package <pattern>;` declaration matching its directory

## Code Style

### File Template
```java
/**
 * {Problem Number}. {Problem Title}
 * Difficulty: {Easy/Medium/Hard}
 * Tags: {tag1}, {tag2}, ...
 * URL: https://leetcode.com/problems/{slug}/
 *
 * Problem Description:
 * {Brief description of what the problem asks}
 */
public class Leetcode{Number} {
    // Solution method(s) here
}
```

### Naming Conventions
- Use descriptive variable names (no single-letter vars except loop indices i, j, k or well-known conventions like l, r for two pointers)
- Solution method: typically `public returnType solution(params)`
- Private helper methods with descriptive names

### Solution Structure
- Include the Big O complexity analysis as a comment above the solution method
- Time complexity: O(?)
- Space complexity: O(?)
- Prefer the most optimal solution; optionally include brute-force as a commented-out alternative

### Testing
- Use `public static void main(String[] args)` for quick manual tests within each file
- Print expected vs actual output clearly
- For proper unit tests, create test files in a `test/` source root

## Algorithm Patterns to Master
1. Arrays & Hashing
2. Two Pointers
3. Sliding Window
4. Stack / Monotonic Stack
5. Binary Search
6. Linked List (Slow/Fast Pointer, Reversal)
7. Trees (DFS, BFS, BST operations)
8. Heap / Priority Queue (Top-K pattern)
9. Backtracking (Subsets, Permutations, Combinations)
10. Dynamic Programming (1D, 2D, Knapsack, LCS, etc.)
11. Graphs (BFS, DFS, Dijkstra, Topological Sort, Union Find)
12. Greedy
13. Intervals (Merge, Insert, Overlap)
14. Math & Geometry
15. Bit Manipulation
16. Trie
17. Design (LRU, LFU, etc.)

## Common Commands
```bash
# Compile a single file
javac src/Leetcode1.java -d out/

# Run a single file
java -cp out/ Leetcode1

# Run with assertions enabled
java -ea -cp out/ Leetcode1
```

## Daily Practice Workflow
1. Pick a problem from the target list
2. Understand the problem and constraints
3. Design the algorithm on paper/whiteboard first
4. Code the solution in the corresponding file
5. Test with edge cases (empty input, single element, large input, negative numbers, etc.)
6. Analyze time and space complexity
7. Review and refactor for clarity
