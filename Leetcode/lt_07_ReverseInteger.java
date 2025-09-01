// lt_07_ReverseInteger.java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Check overflow before multiplying by 10
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;

            rev = rev * 10 + pop;
        }
        return rev;
    }
}

public class lt_07_ReverseInteger {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Examples
        System.out.println(sol.reverse(123));    // 321
        System.out.println(sol.reverse(-123));   // -321
        System.out.println(sol.reverse(120));    // 21

        // Edge cases
        System.out.println(sol.reverse(0));                // 0
        System.out.println(sol.reverse(1534236469));       // 0 (overflow)
        System.out.println(sol.reverse(-2147483648));      // 0 (overflow)
        System.out.println(sol.reverse(1463847412));       // 2147483641 (valid)
    }
}
