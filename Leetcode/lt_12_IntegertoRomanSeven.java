import java.io.*;

// 題目：Integer to Roman
// 給定一個整數 num (1 <= num <= 3999)，請轉換成對應的羅馬數字字串。
class Solution {
    public String intToRoman(int num) {
        // 定義對照表（由大到小）
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();
        // 從最大值開始，一直減，直到 num 變 0
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}

// 本地測試主程式（LeetCode 不需要）
public class lt_12_IntegertoRomanSeven {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter integer (1 <= num <= 3999): ");
        int num = Integer.parseInt(br.readLine().trim());

        Solution sol = new Solution();
        String roman = sol.intToRoman(num);
        System.out.println("Roman numeral = " + roman);
    }
}

/*
解題思路：
1. 題目規則：
   - 羅馬數字由大到小組成，僅在 4、9 類情況使用減法形式 (IV, IX, XL, XC, CD, CM)。
   - 符號 I, X, C, M 最多連續出現 3 次，其餘 (V, L, D) 不可重複。
   - 最大輸入為 3999，所以最多使用到 "MMMCMXCIX"。
2. 解法：
   - 建立一組 values 與 symbols 對照表，從最大值 (1000, "M") 開始處理。
   - 若 num >= values[i]，則減去該值並加入符號，直到 num < values[i]。
   - 依序向下處理，直到 num = 0。
3. 範例：
   num = 3749
   - 減去 1000 → MMM
   - 減去 500 → D → MMMD
   - 減去 100 → CC → MMMDCC
   - 減去 40 → XL → MMMDCCXL
   - 減去 9 → IX → MMMDCCXLIX
   答案 = "MMMDCCXLIX"
4. 複雜度：
   - 每個值最多處理 3~4 次，時間複雜度 O(1)（因為範圍固定）。
   - 空間複雜度 O(1)。
*/
