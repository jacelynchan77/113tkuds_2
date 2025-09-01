// lt_05_LongestPalindromicSubstring.java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        int bestStart = 0, bestLen = 1;

        for (int center = 0; center < n; center++) {
            // Odd-length palindrome (single char center)
            int[] odd = expandFromCenter(s, center, center);

            // Even-length palindrome (between two chars)
            int[] even = expandFromCenter(s, center, center + 1);

            if (odd[1] > bestLen) { bestStart = odd[0]; bestLen = odd[1]; }
            if (even[1] > bestLen) { bestStart = even[0]; bestLen = even[1]; }
        }
        return s.substring(bestStart, bestStart + bestLen);
    }

    // Returns {startIndex, length} of the palindrome expanded from [L..R]
    private int[] expandFromCenter(String s, int L, int R) {
        int n = s.length();
        while (L >= 0 && R < n && s.charAt(L) == s.charAt(R)) {
            L--; R++;
        }
        // When loop breaks, [L+1 .. R-1] is the palindrome
        int start = L + 1;
        int len = R - L - 1;
        return new int[]{start, len};
    }
}

public class lt_05_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Provided examples
        System.out.println(sol.longestPalindrome("babad")); // "bab" or "aba"
        System.out.println(sol.longestPalindrome("cbbd"));  // "bb"

        // Extra quick checks
        System.out.println(sol.longestPalindrome("a"));         // "a"
        System.out.println(sol.longestPalindrome("ac"));        // "a" or "c"
        System.out.println(sol.longestPalindrome("forgeeksskeegfor")); // "geeksskeeg"
        System.out.println(sol.longestPalindrome("abba"));      // "abba"
        System.out.println(sol.longestPalindrome("abccba"));    // "abccba"
    }
}
