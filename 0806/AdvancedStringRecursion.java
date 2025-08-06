
public class AdvancedStringRecursion {

    public static void permute(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                permute(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i));
            }
        }
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (match(text, pattern.substring(2)) || (firstMatch && match(text.substring(1), pattern)));
        } else {
            return firstMatch && match(text.substring(1), pattern.substring(1));
        }
    }

    public static String removeDuplicates(String str) {
        if (str.length() <= 1) {
            return str;
        }
        char first = str.charAt(0);
        String rest = removeDuplicates(str.substring(1));
        if (rest.indexOf(first) == -1) {
            return first + rest;
        } else {
            return rest;
        }
    }

    public static void substrings(String str, String curr) {
        if (str.isEmpty()) {
            if (!curr.isEmpty()) {
                System.out.println(curr);
            }
            return;
        }
        substrings(str.substring(1), curr + str.charAt(0));
        substrings(str.substring(1), curr);
    }

    public static void main(String[] args) {
        System.out.println("Permutations of ABC:");
        permute("ABC", "");

        System.out.println("Match 'aab' with 'c*a*b': " + match("aab", "c*a*b"));

        System.out.println("Remove duplicates from 'banana': " + removeDuplicates("banana"));

        System.out.println("Substrings of 'abc':");
        substrings("abc", "");
    }
}
