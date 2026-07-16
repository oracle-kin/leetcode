package string;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 * Difficulty: Hard
 * Tags: Array, String, Simulation
 * URL: https://leetcode.com/problems/text-justification/
 *
 * Problem Description:
 * Format text so that each line has exactly maxWidth characters and is fully justified.
 */
public class Leetcode68 {

    /**
     * Greedy line packing with space distribution.
     * Time: O(n)  — n is total characters of all words
     * Space: O(n) — result list
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i, lineLen = 0;
            // Pack as many words as possible
            while (j < words.length && lineLen + words[j].length() + (j - i) <= maxWidth) {
                lineLen += words[j].length();
                j++;
            }

            int numWords = j - i;
            StringBuilder sb = new StringBuilder();

            if (numWords == 1 || j == words.length) {
                // Left-justify: single word or last line
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } else {
                // Fully justify
                int totalSpaces = maxWidth - lineLen;
                int spacesBetween = totalSpaces / (numWords - 1);
                int extraSpaces = totalSpaces % (numWords - 1);

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int spaces = spacesBetween + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spaces; s++) sb.append(" ");
                    }
                }
            }

            result.add(sb.toString());
            i = j;
        }

        return result;
    }

    public static void main(String[] args) {
        Leetcode68 sol = new Leetcode68();

        // Test 1
        String[] words1 = { "This", "is", "an", "example", "of", "text", "justification." };
        List<String> res1 = sol.fullJustify(words1, 16);
        System.out.println("Test 1:");
        for (String line : res1) System.out.println("\"" + line + "\"");
        System.out.println("Expected: [\"This    is    an\", \"example  of text\", \"justification.  \"]");

        // Test 2
        String[] words2 = { "What", "must", "be", "acknowledgment", "shall", "be" };
        List<String> res2 = sol.fullJustify(words2, 16);
        System.out.println("Test 2:");
        for (String line : res2) System.out.println("\"" + line + "\"");
    }
}
