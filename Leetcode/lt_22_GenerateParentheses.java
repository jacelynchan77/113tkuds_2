// file: lt_22_GenerateParentheses.java
import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(n, 0, 0, path, ans);
        return ans;
    }

    private void dfs(int n, int open, int close, StringBuilder path, List<String> ans) {
        if (path.length() == 2 * n) {
            ans.add(path.toString());
            return;
        }
        if (open < n) {
            path.append('(');
            dfs(n, open + 1, close, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {
            path.append(')');
            dfs(n, open, close + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

public class lt_22_GenerateParentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generateParenthesis(3));
        System.out.println(sol.generateParenthesis(1));
    }
}

/*
解題邏輯與思路：
1) 目標：生成所有由 n 對括號構成且「合法」的字串。合法定義：任一前綴中 ')' 的數量不可超過 '('，並於結尾兩者數量相等。
2) 方法：回溯（DFS）逐步構造字串，狀態為：
   - open：已放入的 '(' 數量；最多 n。
   - close：已放入的 ')' 數量；任何時刻都必須滿足 close ≤ open，且最多 n。
3) 遞迴轉移：
   - 若 open < n，可放 '('。
   - 若 close < open，可放 ')'。
   - 當字串長度達 2n，收集結果。
4) 正確性：透過限制 close < open，保證任一中間狀態皆不會出現非法前綴；終態長度 2n 且 open=close=n，必為合法括號字串。
5) 複雜度：時間約為生成結果個數的量級（第 n 個卡特蘭數 C_n ≈ 4^n/(n^{3/2}√π)），空間為 O(n)（遞迴深度與臨時路徑）。
*/
