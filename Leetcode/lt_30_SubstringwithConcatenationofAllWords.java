// file: lt_30_SubstringwithConcatenationofAllWords.java
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;

        int n = s.length();
        int m = words.length;
        int w = words[0].length();
        int needLen = m * w;
        if (n < needLen) return ans;

        // 統計每個單字需要的次數
        Map<String, Integer> need = new HashMap<>();
        for (String t : words) need.put(t, need.getOrDefault(t, 0) + 1);

        // 依據不同偏移量進行滑動視窗：0..w-1
        for (int offset = 0; offset < w; offset++) {
            int left = offset, count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + w <= n; right += w) {
                String word = s.substring(right, right + w);

                if (!need.containsKey(word)) {
                    // 碰到非目標單字，整窗清空，從下一段開始
                    window.clear();
                    count = 0;
                    left = right + w;
                    continue;
                }

                // 納入當前單字
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // 若某單字超標，縮小左邊直到不超標
                while (window.get(word) > need.get(word)) {
                    String drop = s.substring(left, left + w);
                    window.put(drop, window.get(drop) - 1);
                    count--;
                    left += w;
                }

                // 若剛好包含 m 個單字，紀錄起點
                if (count == m) {
                    ans.add(left);
                    // 視窗右移一格（移出最左單字），繼續找下一個
                    String drop = s.substring(left, left + w);
                    window.put(drop, window.get(drop) - 1);
                    count--;
                    left += w;
                }
            }
        }
        return ans;
    }
}

public class lt_30_SubstringwithConcatenationofAllWords {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // [0, 9]
        System.out.println(sol.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"})); // []
        System.out.println(sol.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})); // [6, 9, 12]
    }
}

/*
解題邏輯與思路：
1) 問題：尋找所有起始索引，使得 s 的某段子字串可由 words 內所有單字（長度相同）組成，順序任意且每字使用次數符合 words 的頻率。
2) 核心觀念：固定單字長度 w，採「多起點滑動視窗」。對每個偏移 offset∈[0..w-1]，只檢查對齊該偏移的切片（right 以 w 為步長）。
3) 作法：
   - 先建立 need 映射，記錄每個單字需要的次數。
   - 於每個 offset，維護區間 [left, right) 內由 w 長度切片而成的「單字窗口」：
     a) 右端每次讀入一個長度為 w 的 word。
     b) 若 word 不在 need 中，清空窗口與計數，left 跳到 right+w。
     c) 否則將 word 納入 window，若某字次數超過 need，從左側不停移出（每次移出一個 w 字元的單字）直到不超標。
     d) 當窗口內單字數量 count == m（words 的個數），表示從 left 開始的一段剛好匹配，紀錄 left；接著左端移出一個單字，繼續往後找下一個解。
4) 為何要分 w 個偏移？因為所有候選分段的邊界必在某個 offset（mod w）上，逐一掃描可覆蓋完整情況，同時確保時間效率。
5) 複雜度：
   - 外層 w 次偏移；內層每個位置右指針與左指針各走一次，總體 O(n)。
   - 單字比對與哈希更新為 O(1) 攤銷，故總時間約 O(w * n) ≈ O(n)；空間 O(U)，U 為不同單字數量。
*/
