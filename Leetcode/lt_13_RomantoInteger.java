import java.io.*;
import java.util.*;

// 題目：Roman to Integer
// 給定一個羅馬數字字串，轉換成整數 (範圍 1 <= num <= 3999)。
class Solution {
    public int romanToInt(String s) {
        // 建立對照表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            // 若下一個字元比當前大，則減去當前值
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                total -= value;
            } else {
                total += value;
            }
        }
        return total;
    }
}

// 本地測試主程式（LeetCode 不需要）
public class lt_13_RomantoInteger {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Roman numeral (e.g. MCMXCIV): ");
        String roman = br.readLine().trim();

        Solution sol = new Solution();
        int num = sol.romanToInt(roman);
        System.out.println("Integer = " + num);
    }
}

/*
解題思路：
1. 題目規則：
   - 羅馬數字一般由大到小排列，直接加總即可。
   - 特殊情況：若較小的數字出現在較大的數字前，則須用減法。
     - I 可放在 V(5) 和 X(10) 前 → 4, 9
     - X 可放在 L(50) 和 C(100) 前 → 40, 90
     - C 可放在 D(500) 和 M(1000) 前 → 400, 900
2. 演算法：
   - 從左到右讀字串，對每個字元取得數值。
   - 若當前數值 < 下一個數值，則 total -= 當前值。
   - 否則 total += 當前值。
3. 範例：
   - s = "MCMXCIV"
   - M=1000 → total=1000
   - C=100 < M=1000 → total=900
   - M=1000 → total=1900
   - X=10 < C=100 → total=1890
   - C=100 → total=1990
   - I=1 < V=5 → total=1989
   - V=5 → total=1994
   → 最終答案 1994
4. 複雜度：
   - 時間複雜度 O(n)，僅需一次迴圈。
   - 空間複雜度 O(1)，使用固定大小的對照表。
*/
