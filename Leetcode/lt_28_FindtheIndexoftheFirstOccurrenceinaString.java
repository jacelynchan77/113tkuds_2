// file: lt_28_FindtheIndexoftheFirstOccurrenceinaString.java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

public class lt_28_FindtheIndexoftheFirstOccurrenceinaString {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.strStr("sadbutsad", "sad"));   // 0
        System.out.println(sol.strStr("leetcode", "leeto"));  // -1
    }
}

/*
解題邏輯與思路：
1) 題目要求在字串 haystack 中找出子字串 needle 第一次出現的位置，若不存在回傳 -1。
2) 簡單解法：暴力掃描。
   - 設 needle 長度為 m，haystack 長度為 n。
   - 對每個可能的起始位置 i (0 ≤ i ≤ n-m)，取 haystack 的子字串 [i, i+m) 與 needle 比較。
   - 若相等，立即回傳 i。
   - 若整個迴圈跑完都沒有找到，回傳 -1。
3) 複雜度分析：
   - 時間複雜度：O((n-m+1)*m)，最壞情況每次比較 m 個字元。
   - 空間複雜度：O(1)（若用 substring 會額外產生物件，但本質為常數額外空間）。
4) 優化方法：可使用 KMP 演算法降至 O(n+m)，但本題 n, m 最大 10^4，暴力解也能接受。
*/
