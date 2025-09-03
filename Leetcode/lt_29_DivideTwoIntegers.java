// file: lt_29_DivideTwoIntegers.java
class Solution {
    public int divide(int dividend, int divisor) {
        // 特殊情況：避免溢位
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 判斷結果正負號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 使用 long 轉型避免溢位，並取絕對值
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;
        while (a >= b) {
            long temp = b, multiple = 1;
            // 倍增 divisor，直到超過 dividend
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }
}

public class lt_29_DivideTwoIntegers {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.divide(10, 3));   // 3
        System.out.println(sol.divide(7, -3));   // -2
        System.out.println(sol.divide(-2147483648, -1)); // 2147483647 (避免溢位)
    }
}

/*
解題邏輯與思路：
1) 題目要求實作整數除法，不能用 *, /, %，只能用加減與位元操作。
2) 關鍵技巧：利用「減法 + 倍增」模擬除法。
   - 將 dividend 與 divisor 都轉成 long 型別並取絕對值，避免溢位。
   - 每次嘗試用 divisor 倍增 (左移) 直到超過 dividend 的範圍，然後從 dividend 減去這個最大可行的值，累加對應的倍數到 result。
   - 重複直到 dividend < divisor。
3) 結果正負號由 (dividend < 0) XOR (divisor < 0) 判斷。
4) 特殊情況處理：
   - 當 dividend = -2^31 且 divisor = -1，結果會超過 int 最大值，需回傳 Integer.MAX_VALUE。
5) 複雜度：
   - 時間複雜度：O(log n)，因為每次內層循環倍增 divisor。
   - 空間複雜度：O(1)。
*/
