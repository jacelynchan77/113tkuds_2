// file name: LC17_PhoneCombos_CSShift.java

import java.util.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] MAP = {
        "",     // 0 (unused)
        "",     // 1 (unused)
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";
        // remove spaces just in case (e.g., "2 3" → "23")
        digits = digits.replaceAll("\\s+", "");

        if (digits.isEmpty()) {
            // 空字串：不輸出任何組合（0 行）
            return;
        }

        // 若含非 2–9 的字元，可選擇直接結束或忽略。
        // 這裡按照題意假設輸入只含 '2'–'9'。

        StringBuilder path = new StringBuilder(digits.length());
        backtrack(digits, 0, path);
    }

    private static void backtrack(String digits, int idx, StringBuilder path) {
        if (idx == digits.length()) {
            System.out.println(path.toString());
            return;
        }

        char d = digits.charAt(idx);
        String letters = MAP[d - '0']; // 對應此位數字的字母

        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            backtrack(digits, idx + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
