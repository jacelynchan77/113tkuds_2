// file name: LC26_RemoveDuplicates_Scores.java
import java.io.*;
import java.util.*;

/** 壓縮已排序成績單：移除重複學號，使每個值只出現一次
 *  複雜度：時間 O(n)，空間 O(1)
 *
 *  輸入：
 *    n
 *    n 個已排序整數（可分多行，以空白分隔）
 *
 *  輸出：
 *    新長度
 *    前段結果（僅輸出前新長度個元素）
 *
 *  範例：
 *    輸入:
 *      7
 *      0 0 1 1 1 2 2
 *    輸出:
 *      3
 *      0 1 2
 */
public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn(System.in);
        StringBuilder out = new StringBuilder();

        Integer nObj = in.nextIntNullable();
        if (nObj == null) return; // 無輸入
        int n = nObj;

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        int len = removeDuplicatesInPlace(a, n);

        // 輸出新長度
        out.append(len).append('\n');

        // 輸出前段內容（若 len=0 則印空行）
        for (int i = 0; i < len; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        out.append('\n');

        System.out.print(out.toString());
    }

    /** 以就地方式移除重複值，回傳新長度；保留前段唯一元素 */
    static int removeDuplicatesInPlace(int[] a, int n) {
        if (n == 0) return 0;
        int write = 1;           // 下一個可寫位置
        for (int i = 1; i < n; i++) {
            if (a[i] != a[write - 1]) {
                a[write++] = a[i];
            }
        }
        return write;
    }

    /* 穩健讀取：支援空白/空行、Windows \r\n，多行輸入 */
    static class FastIn {
        private final BufferedReader br;
        private StringTokenizer st;

        FastIn(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        Integer nextIntNullable() throws IOException {
            String s = next();
            return (s == null) ? null : Integer.parseInt(s);
        }

        int nextInt() throws IOException {
            String s = next();
            if (s == null) throw new EOFException("Expected more integers");
            return Integer.parseInt(s);
        }
    }
}
