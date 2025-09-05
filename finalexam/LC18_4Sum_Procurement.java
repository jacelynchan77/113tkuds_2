// file name: LC18_4Sum_Procurement.java

import java.io.*;
import java.util.*;

/**
 * 4Sum: 找出所有不重複的四元組，使其總和等於 target。
 * 讀入：
 *   第一行：n target
 *   第二行起：n 個整數（可分多行）
 * 輸出：
 *   每行一組升序四元組；若無解則不輸出任何行。
 *
 * 時間複雜度：O(n^3)；空間：O(1)（不含輸出）
 */
public class LC18_4Sum_Procurement {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = (int) fs.nextLong();
        long target = fs.nextLong();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        Arrays.sort(a); // 升序，便於去重與輸出

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue; // 去重 i

            // 剪枝（可選）：最小和/最大和範圍檢查
            long min1 = a[i] + a[i + 1] + a[i + 2] + a[i + 3];
            if (min1 > target) break;
            long max1 = a[i] + a[n - 1] + a[n - 2] + a[n - 3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue; // 去重 j

                // 剪枝（可選）
                long min2 = a[i] + a[j] + a[j + 1] + a[j + 2];
                if (min2 > target) break;
                long max2 = a[i] + a[j] + a[n - 1] + a[n - 2];
                if (max2 < target) continue;

                long need = target - a[i] - a[j];
                int L = j + 1, R = n - 1;

                while (L < R) {
                    long sumLR = a[L] + a[R];
                    if (sumLR == need) {
                        out.append(a[i]).append(' ')
                           .append(a[j]).append(' ')
                           .append(a[L]).append(' ')
                           .append(a[R]).append('\n');

                        // 內層去重 L, R
                        long vL = a[L], vR = a[R];
                        while (L < R && a[L] == vL) L++;
                        while (L < R && a[R] == vR) R--;
                    } else if (sumLR < need) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        System.out.print(out.toString());
    }

    // 以 BufferedReader + StringTokenizer 避免 Scanner 的 InputMismatch 與效能問題
    private static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;
        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        long nextLong() throws IOException {
            String s = next();
            if (s == null) throw new EOFException();
            return Long.parseLong(s);
        }
    }
}
