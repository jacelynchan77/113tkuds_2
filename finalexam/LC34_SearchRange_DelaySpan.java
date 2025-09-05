// file name: LC34_SearchRange_DelaySpan.java
import java.io.*;
import java.util.*;

/** 在已排序（非遞減）的整數陣列中，找出 target 的首末索引；不存在輸出 -1 -1
 *  時間：O(log n)；空間：O(1)
 *
 *  輸入：
 *    n target
 *    n 個整數（可分多行）
 *
 *  輸出：
 *    兩個索引（首、末）
 */
public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn(System.in);

        Integer nObj = in.nextIntNullable();
        if (nObj == null) return; // 無輸入
        int n = nObj;
        int target = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();

        int first = lowerBound(a, target);
        if (first == n || a[first] != target) {
            System.out.println("-1 -1");
            return;
        }
        int last = upperBound(a, target) - 1;
        System.out.println(first + " " + last);
    }

    /** 回傳第一個 >= key 的索引；若皆小於 key，回傳 n */
    static int lowerBound(int[] a, int key) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + ((r - l) >>> 1);
            if (a[m] >= key) r = m;
            else l = m + 1;
        }
        return l;
    }

    /** 回傳第一個 > key 的索引；若皆 <= key，回傳 n */
    static int upperBound(int[] a, int key) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + ((r - l) >>> 1);
            if (a[m] <= key) l = m + 1;
            else r = m;
        }
        return l;
    }

    /* 穩健輸入：支援多行、任意空白、Windows \r\n */
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
