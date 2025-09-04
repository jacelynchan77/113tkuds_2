import java.io.*;
import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - st.peek());
                }
            }
        }
        return maxLen;
    }
}

public class lt_32_LongestValidParentheses {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) return;
        s = s.trim();
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
        }
        System.out.println(new Solution().longestValidParentheses(s));
    }
}

/*
解題邏輯與思路
使用索引堆疊法：
1) 先壓入基準值 -1。遍歷字串：
   - 遇到 '('：把索引 i 推入堆疊。
   - 遇到 ')'：先彈出一個索引；若堆疊為空，將當前索引 i 作為新的基準壓入；否則以 i - 堆疊頂端索引 計算目前有效長度並更新最大值。
2) 基準值代表「最後一個不匹配位置」，可正確量測之後連續有效區間的長度。
時間 O(n)，空間 O(n)（最壞情況索引堆疊）。
*/
