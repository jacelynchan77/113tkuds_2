// lt_08_StringtoInteger.java
class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length();

        // Step 1: Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') i++;
        if (i == n) return 0;

        // Step 2: Check sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Convert digits
        long num = 0; // use long to detect overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;

            // Step 4: Clamp to 32-bit signed int range
            if (sign == 1 && num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -num < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int)(sign * num);
    }
}

public class lt_08_StringtoInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Examples
        System.out.println(sol.myAtoi("42"));          // 42
        System.out.println(sol.myAtoi("   -042"));     // -42
        System.out.println(sol.myAtoi("1337c0d3"));    // 1337
        System.out.println(sol.myAtoi("0-1"));         // 0
        System.out.println(sol.myAtoi("words and 987"));// 0

        // Extra edge cases
        System.out.println(sol.myAtoi("2147483647"));   // 2147483647
        System.out.println(sol.myAtoi("2147483648"));   // 2147483647 (clamped)
        System.out.println(sol.myAtoi("-2147483648"));  // -2147483648
        System.out.println(sol.myAtoi("-2147483649"));  // -2147483648 (clamped)
        System.out.println(sol.myAtoi(""));             // 0
        System.out.println(sol.myAtoi("   +0 123"));    // 0
    }
}
