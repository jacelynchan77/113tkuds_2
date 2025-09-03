// file: lt_17_LetterCombinationsofaPhoneNumber.java
import java.util.*;


class Solution {
    private static final String[] MAP = new String[]{
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path, ans);
        return ans;
    }

    private void dfs(String digits, int idx, StringBuilder path, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(path.toString());
            return;
        }
        String letters = MAP[digits.charAt(idx) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, idx + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }
}


public class lt_17_LetterCombinationsofaPhoneNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();
        if (args.length > 0) {
            // 支援兩種：23 或 digits=23
            String s = args[0];
            int eq = s.indexOf('=');
            if (eq >= 0) s = s.substring(eq + 1).trim();
            s = stripQuotes(s);
            System.out.println(sol.letterCombinations(s));
        } else {
           
            System.out.println(sol.letterCombinations("23")); // 期望 [ad, ae, af, bd, be, bf, cd, ce, cf]
            System.out.println(sol.letterCombinations(""));   // 期望 []
            System.out.println(sol.letterCombinations("2"));  // 期望 [a, b, c]
        }
    }

    private static String stripQuotes(String s) {
        if (s.length() >= 2 && (s.charAt(0) == '"' || s.charAt(0) == '\'')) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}

/*
解題思路（中文）：
1) 建立數字到字母的映射：2→"abc"、3→"def"…7→"pqrs"、8→"tuv"、9→"wxyz"。
2) 用回溯（DFS）逐位擴展：idx 指目前處理到第幾位，對該位每個候選字母都嘗試加入 path，遞迴到下一位；到尾就收集成品。
3) digits 為空直接回傳空列。
時間複雜度最壞約 4^n；空間複雜度 O(n)（遞迴深度）。
*/
