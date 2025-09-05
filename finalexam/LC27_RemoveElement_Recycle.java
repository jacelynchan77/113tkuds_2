// file name: LC27_RemoveElement_Recycle.java
import java.io.*;
import java.util.*;

/** 移除陣列中所有等於 val 的元素，就地覆寫，回傳新長度與前段結果
 *  複雜度：O(n) 時間，O(1) 額外空間
 *
 *  輸入：
 *    n val
 *    n 個整數（可分多行）
 *
 *  輸出：
 *    新長度
 *    前段結果（僅輸出前新長度個元素）
 *
 *  範例：
 *    輸入:
 *      4 2
 *      3 2 2 3
 *    輸出:
 *      2
 *      3 3
 */
public class LC27_RemoveElement_Recycle {

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn(System.in);
        StringBuilder out = new StringBuilder();

        Integer nObj = in.nextIntNullable();
        if (nObj == null) return; // 無輸入
        int n = nObj;
        int val = in.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();

        int len = removeElement(nums, n, val);

        // 輸出新長度
        out.append(len).append('\n');

        // 輸出前段結果
        for (int i = 0; i < len; i++) {
            if (i > 0) out.append(' ');
            out.append(nums[i]);
        }
        out.append('\n');

        System.out.print(out.toString());
    }

    /** 單指針覆寫，不等於 val 的元素保留 */
    static int removeElement(int[] nums, int n, int val) {
        int write = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }

    /* 穩健輸入：支援空白/換行、Windows \r\n */
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
            if (s == null) throw new EOFException("Expected integer");
            return Integer.parseInt(s);
        }
    }
}
