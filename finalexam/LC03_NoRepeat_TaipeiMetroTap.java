// file name: LC03_NoRepeat_TaipeiMetroTap.java

import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        // Map<char, lastIndex>
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果 c 曾經出現過，且位置 >= left
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                left = lastIndex.get(c) + 1; // 縮左界
            }

            lastIndex.put(c, right); // 更新最新位置
            ans = Math.max(ans, right - left + 1); // 更新答案
        }

        System.out.println(ans);
    }
}
