// lt_09_PalindromeNumber.java
class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers and numbers ending with 0 (but not 0 itself) cannot be palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        while (x > reversedHalf) {
            int pop = x % 10;
            reversedHalf = reversedHalf * 10 + pop;
            x /= 10;
        }

        // For even length numbers: x == reversedHalf
        // For odd length numbers: x == reversedHalf/10 (middle digit ignored)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}

public class lt_09_PalindromeNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Examples
        System.out.println(sol.isPalindrome(121));   // true
        System.out.println(sol.isPalindrome(-121));  // false
        System.out.println(sol.isPalindrome(10));    // false

        // Extra tests
        System.out.println(sol.isPalindrome(0));         // true
        System.out.println(sol.isPalindrome(12321));     // true
        System.out.println(sol.isPalindrome(123321));    // true
        System.out.println(sol.isPalindrome(123421));    // false
    }
}
