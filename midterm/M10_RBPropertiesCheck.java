import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] val = new int[n];
        char[] col = new char[n];

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            String c = st.hasMoreTokens() ? st.nextToken() : "B"; // 安全處理
            val[i] = v;
            // 根據題目說明，null (-1) 視為黑色節點。
            col[i] = (v == -1) ? 'B' : (c.isEmpty() ? 'B' : c.charAt(0));
        }

        // 1) 根節點必須為黑色（空樹視為有效）
        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 不允許連續兩個紅色節點
        int redRedIndex = firstRedRedViolation(val, col);
        if (redRedIndex != -1) {
            System.out.println("RedRedViolation at index " + redRedIndex);
            return;
        }

        // 3) 黑高在所有路徑上必須一致
        if (!blackHeightConsistent(val, col)) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // ---------- 性質 2: 找出第一個紅色節點且其子節點也是紅色的情況 ----------
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 單次掃描所有陣列索引 (0..n-1)。
     * 2. 每個節點最多檢查 2 個子節點，操作為 O(1)。
     * 3. 因此總體時間複雜度為 O(n)。
     */
    private static int firstRedRedViolation(int[] val, char[] col) {
        int n = val.length;
        for (int i = 0; i < n; i++) {
            if (isRed(i, val, col)) {
                int L = 2 * i + 1, R = 2 * i + 2;
                if (isRed(L, val, col) || isRed(R, val, col)) {
                    return i; // 以層序遍歷順序找到第一個違規的索引
                }
            }
        }
        return -1;
    }

    private static boolean isRed(int idx, int[] val, char[] col) {
        return idx >= 0 && idx < val.length && val[idx] != -1 && col[idx] == 'R';
    }

    // ---------- 性質 3: 黑高一致性檢查 ----------
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 每個陣列位置對應一個樹節點，每個節點僅被拜訪一次。
     * 2. 每個節點操作為 O(1)，外加兩次遞迴呼叫（分別處理不重疊的子樹）。
     * 3. 因此總體時間複雜度為 O(n)。遞迴空間為 O(h)，其中 h 為樹的高度。
     */
    private static boolean blackHeightConsistent(int[] val, char[] col) {
        return blackHeightOrFail(0, val, col) != FAIL; // 從根節點索引 0 開始
    }

    // 回傳以索引 i 為根的子樹黑高；若某處不一致，回傳 FAIL。
    // 慣例: NIL (null 或越界 / -1) 視為黑葉子，黑高為 1。
    private static final int FAIL = Integer.MIN_VALUE / 2;

    private static int blackHeightOrFail(int i, int[] val, char[] col) {
        int n = val.length;
        // NIL 葉子 (陣列外或 -1) → 黑葉，黑高 = 1
        if (i >= n || val[i] == -1) return 1;

        int left = blackHeightOrFail(2 * i + 1, val, col);
        if (left == FAIL) return FAIL;

        int right = blackHeightOrFail(2 * i + 2, val, col);
        if (right == FAIL) return FAIL;

        if (left != right) return FAIL;

        int selfBlack = (col[i] == 'B') ? 1 : 0;
        return left + selfBlack;
    }
}
