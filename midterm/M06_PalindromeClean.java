import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 使用雙指標掃描整個字串。
     * 2. 每個字元最多被訪問一次。
     * 3. 因此總體時間複雜度為 O(n)，其中 n = 字串長度。
     */
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);

            // Skip non-letters
            if (!Character.isLetter(cLeft)) {
                left++;
                continue;
            }
            if (!Character.isLetter(cRight)) {
                right--;
                continue;
            }

            // Compare ignoring case
            if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
