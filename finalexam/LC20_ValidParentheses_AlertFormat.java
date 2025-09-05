// file name: LC20_ValidParentheses_AlertFormat.java

import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        // Map penutup → pembuka
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { 
                // c adalah penutup, cek stack
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false; // mismatch
                }
                stack.pop();
            } else { 
                // c adalah pembuka
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
