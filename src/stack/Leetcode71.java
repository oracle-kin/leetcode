package stack;

import java.util.Stack;

/**
 * 71. Simplify Path
 * Difficulty: Medium
 * Tags: String, Stack
 * URL: https://leetcode.com/problems/simplify-path/
 *
 * Problem Description:
 * Given an absolute Unix-style path, transform it into its simplified canonical form.
 * Rules:
 * - Multiple slashes are replaced by a single slash.
 * - A single period '.' refers to the current directory.
 * - A double period '..' refers to the directory up a level.
 * - The canonical path must start with a single slash '/' and no trailing slash
 *   (unless it's the root directory).
 */
public class Leetcode71 {

    /**
     * Time: O(n)  — split and iterate through components
     * Space: O(n) — stack stores directory names
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for (String comp : components) {
            if (comp.isEmpty() || comp.equals(".")) {
                continue;
            } else if (comp.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(comp);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Leetcode71 sol = new Leetcode71();

        System.out.println("Test 1: " + sol.simplifyPath("/home/") + " | Expected: /home");
        System.out.println("Test 2: " + sol.simplifyPath("/home//foo/") + " | Expected: /home/foo");
        System.out.println("Test 3: " + sol.simplifyPath("/home/user/Documents/../Pictures") + " | Expected: /home/user/Pictures");
        System.out.println("Test 4: " + sol.simplifyPath("/../") + " | Expected: /");
        System.out.println("Test 5: " + sol.simplifyPath("/.../a/../b/c/../d/./") + " | Expected: /.../b/d");
    }
}
