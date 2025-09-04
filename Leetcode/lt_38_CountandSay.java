
class Solution {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char prev = res.charAt(0);
            int count = 1;
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == prev) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    prev = res.charAt(j);
                    count = 1;
                }
            }
            sb.append(count).append(prev);
            res = sb.toString();
        }
        return res;
    }
}

public class lt_38_CountandSay {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countAndSay(1)); // "1"
        System.out.println(sol.countAndSay(4)); // "1211"
        System.out.println(sol.countAndSay(6)); // "312211"
    }
}

/*
解題邏輯與思路
1) 基本情況：n=1 → "1"。
2) 遞迴 / 疊代：從 "1" 開始，每次對前一個字串做 run-length encoding（統計連續相同字元的數量與字元）。
   - 例如 "21" → "1211"（一個 '2'，一個 '1'）。
3) 重複 n-1 次即可得到 countAndSay(n)。
時間複雜度：O(Ln)，Ln 為第 n 項字串長度；空間 O(Ln)。
*/
