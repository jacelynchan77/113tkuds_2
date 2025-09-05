// file name: LC33_SearchRotated_RentHot.java
import java.io.*;
import java.util.*;

/** 在被旋轉的升序陣列中尋找 target 的索引，找不到回 -1
 *  複雜度：O(log n) 時間，O(1) 空間
 *
 *  輸入：
 *    n target
 *    n 個整數（可分多行）
 *
 *  輸出：
 *    索引或 -1
 *
 *  範例：
 *    輸入:
 *      7 0
 *      4 5 6 7 0 1 2
 *    輸出:
 *      4
 */
public class LC33_SearchRotated_RentHot {

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn(System.in);

        Integer nObj = in.nextIntNullable();
        if (nObj == null) return; // 無輸入
        int n = nObj;
        int target = in.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();

        int ans = searchRotated(nums, target);
        System.out.println(ans);
    }

    /** 改良二分：每次判定哪一半是有序的，據此縮小範圍（假設無重複） */
    static int searchRotated(int[] a, int target) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (a[mid] == target) return mid;

            // 左半有序
            if (a[l] <= a[mid]) {
                if (a[l] <= target && target < a[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { // 右半有序
                if (a[mid] < target && target <= a[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
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
