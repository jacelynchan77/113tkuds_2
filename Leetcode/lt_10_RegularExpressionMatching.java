// lt_10_RegularExpressionMatching.java
class Solution {
    // Time: O(m*n), Space: O(m*n)
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        // Patterns like a*, a*b*, a*b*c* can match empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);

                if (pc == '*') {
                    // Zero of prev char
                    dp[i][j] = dp[i][j - 2];

                    // One or more of prev char (if it matches s[i-1])
                    char prev = p.charAt(j - 2);
                    if (matches(s.charAt(i - 1), prev)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s.charAt(i - 1), pc)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean matches(char sc, char pc) {
        return pc == '.' || pc == sc;
    }
}

public class lt_10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Provided examples
        System.out.println(sol.isMatch("aa", "a"));   // false
        System.out.println(sol.isMatch("aa", "a*"));  // true
        System.out.println(sol.isMatch("ab", ".*"));  // true

        // Extra tests
        System.out.println(sol.isMatch("aab", "c*a*b"));     // true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(sol.isMatch("mississippi", "mis*is*ip*.")); // true
        System.out.println(sol.isMatch("", "c*"));           // true
        System.out.println(sol.isMatch("ab", ".*c"));        // false
        System.out.println(sol.isMatch("aaa", "a*a"));       // true
    }
}
