// lt_06_ZigzagConversion.java
class Solution {
    public String convert(String s, int numRows) {
        // Trivial cases
        if (numRows == 1 || numRows >= s.length()) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0;          // current row
        int step = 1;       // +1 going down, -1 going up

        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));

            // turn around at top/bottom
            if (r == 0) step = 1;
            else if (r == numRows - 1) step = -1;

            r += step;
        }

        // concatenate all rows
        StringBuilder ans = new StringBuilder(s.length());
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }
}

public class lt_06_ZigzagConversion {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        System.out.println(sol.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR

        // Example 2
        System.out.println(sol.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI

        // Example 3
        System.out.println(sol.convert("A", 1)); // A

        // Extra quick checks
        System.out.println(sol.convert("ABCD", 2)); // ACBD
        System.out.println(sol.convert("ABCD", 3)); // ABDC
        System.out.println(sol.convert("HELLO.WORLD", 5));
    }
}
