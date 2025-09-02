import java.io.*;

// 題目：Longest Common Prefix
// 給定一組字串，找出它們的最長共同前綴。如果沒有，回傳空字串 ""。
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始逐一比較
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到能配對上當前字串的開頭
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

// 本地測試主程式（LeetCode 不需要）
public class lt_14_LongestCommonPrefix {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter strings separated by space (e.g. flower flow flight): ");
        String[] strs = br.readLine().trim().split("\\s+");

        Solution sol = new Solution();
        String prefix = sol.longestCommonPrefix(strs);
        System.out.println("Longest Common Prefix = \"" + prefix + "\"");
    }
}

/*
解題思路：
1. 題目要求：找出多個字串的最長共同前綴。
2. 方法：水平掃描
   - 假設第一個字串為初始 prefix。
   - 依序和每個字串比較，若不符合，就縮短 prefix（刪掉最後一個字元）。
   - 重複直到所有字串都符合 prefix，或 prefix 變空字串。
3. 範例：
   - strs = ["flower","flow","flight"]
   - prefix = "flower"
   - 和 "flow" 比較 → "flow" 不以 "flower" 開頭 → 縮短成 "flow"
   - 和 "flight" 比較 → "flight" 不以 "flow" 開頭 → 縮短成 "flo" → "fl"
   - 最後得到 "fl"
4. 時間複雜度：
   - 最差情況：O(S)，其中 S 是所有字元總數。
   - 空間複雜度：O(1)。
*/
