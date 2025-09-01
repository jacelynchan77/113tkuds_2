// lt_03_LongestSubstringWithoutRepeatingCharacters.java
import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> last = new HashMap<>();
        int best = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // if c appeared inside current window, move left just past its last index
            if (last.containsKey(c) && last.get(c) >= left) {
                left = last.get(c) + 1;
            }

            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}

public class lt_03_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Provided examples
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3

        // Extra checks
        System.out.println(sol.lengthOfLongestSubstring(""));              // 0
        System.out.println(sol.lengthOfLongestSubstring("dvdf"));          // 3 ("vdf")
        System.out.println(sol.lengthOfLongestSubstring("abba"));          // 2 ("ab"/"ba")
        System.out.println(sol.lengthOfLongestSubstring("a b!a?c d!"));    // 6 ("b!a?c ")
    }
}
