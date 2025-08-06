
public class RecursionVsIteration {

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int prod = 1;
        for (int n : arr) {
            prod *= n;
        }
        return prod;
    }

    public static int vowelsRecursive(String s, int index) {
        if (index == s.length()) {
            return 0;
        }
        char c = Character.toLowerCase(s.charAt(index));
        int add = "aeiou".indexOf(c) != -1 ? 1 : 0;
        return add + vowelsRecursive(s, index + 1);
    }

    public static int vowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static boolean bracketsRecursive(String s, int index, int count) {
        if (count < 0) {
            return false;
        }
        if (index == s.length()) {
            return count == 0;
        }
        char c = s.charAt(index);
        if (c == '(') {
            return bracketsRecursive(s, index + 1, count + 1);
        } else if (c == ')') {
            return bracketsRecursive(s, index + 1, count - 1);
        } else {
            return bracketsRecursive(s, index + 1, count);
        }
    }

    public static boolean bracketsIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println("Binomial C(5,2) Recursive: " + binomialRecursive(5, 2));
        System.out.println("Binomial C(5,2) Iterative: " + binomialIterative(5, 2));

        int[] arr = {2, 3, 4};
        System.out.println("Product Recursive: " + productRecursive(arr, 0));
        System.out.println("Product Iterative: " + productIterative(arr));

        System.out.println("Vowels Recursive in 'Education': " + vowelsRecursive("Education", 0));
        System.out.println("Vowels Iterative in 'Education': " + vowelsIterative("Education"));

        System.out.println("Brackets Recursive '(()())': " + bracketsRecursive("(()())", 0, 0));
        System.out.println("Brackets Iterative '(()())': " + bracketsIterative("(()())"));
    }
}
