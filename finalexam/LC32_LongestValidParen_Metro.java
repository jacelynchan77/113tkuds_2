// file name: LC32_LongestValidParen_Metro.java

import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        // 初始棧底放 -1，作為基準
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);  // push index of '('
            } else { // c == ')'
                stack.pop();   // 嘗試配對一個 '('
                if (stack.isEmpty()) {
                    // 沒有可配對 → 設置新基準
                    stack.push(i);
                } else {
                    // 當前長度 = i - 棧頂
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
