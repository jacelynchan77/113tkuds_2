// file: lt_20_ValidParentheses.java
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }
}

public class lt_20_ValidParentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isValid("()"));       // true
        System.out.println(sol.isValid("()[]{}"));   // true
        System.out.println(sol.isValid("(]"));       // false
        System.out.println(sol.isValid("([])"));     // true
        System.out.println(sol.isValid("([)]"));     // false
    }
}

/*
解題思路：
1) 使用堆疊（stack）來匹配括號。
2) 當遇到左括號 '('、'{'、'[' 時，把對應的右括號壓入堆疊。
   例如遇到 '(' 就 push ')', 遇到 '{' 就 push '}'。
3) 當遇到右括號時，檢查堆疊是否為空，或彈出的元素是否與當前字元匹配：
   - 若不匹配或堆疊為空，則字串無效。
4) 最後若堆疊為空，代表所有括號都正確配對，回傳 true；否則回傳 false。
時間複雜度：O(n)，只需遍歷一次字串。
空間複雜度：O(n)，最壞情況堆疊存放所有括號。
*/
