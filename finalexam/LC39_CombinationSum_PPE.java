// file name: LC39_CombinationSum_PPE.java
import java.io.*;
import java.util.*;

/**
 * 防災物資採購（組合總和）：
 *  - I 版：同一價格可重複使用（無限次）
 *  - II 版：每個價格最多用一次，且去除重複組合
 *
 * 輸入：
 *   第一行：n target
 *   第二行：n 個整數（可含重複）
 *
 * 輸出（每行一組、組內升序，數字以空白分隔）：
 *   先輸出所有 I 版的解，每行一組；
 *   接著輸出一行空白分隔的 "----" 作為分隔線；
 *   再輸出所有 II 版的解，每行一組。
 *
 * 若某版無解，該版就不輸出任何組合（依然會輸出分隔線）。
 *
 * 範例（對應題敘兩個例子）：
 *   Input:
 *     4 7
 *     2 3 6 7
 *   Output:
 *     2 2 3
 *     7
 *     ----
 *     7
 *
 *   Input:
 *     7 8
 *     10 1 2 7 6 1 5
 *   Output:
 *     1 1 6
 *     1 2 5
 *     1 7
 *     2 6
 *     ----
 *     1 1 6
 *     1 2 5
 *     1 7
 *     2 6
 *
 * 時間複雜度：指數級（回溯）；預處理排序 O(n log n)
 * 註：為符合「分別輸出」的要求，程式一次列出 I 與 II 的所有解，中間以 "----" 分隔。
 */
public class LC39_CombinationSum_PPE {

    public static void main(String[] args) throws Exception {
        FastIn in = new FastIn(System.in);

        Integer nObj = in.nextIntNullable();
        if (nObj == null) return; // 無輸入
        int n = nObj;
        int target = in.nextInt();

        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = in.nextInt();

        Arrays.sort(candidates);

        // Combination Sum I（可重複使用）
        List<List<Integer>> ansI = combinationSumI(candidates, target);

        // 輸出 I 版
        printAns(ansI);

        // 分隔線
        System.out.println("----");

        // Combination Sum II（每數最多用一次，需去重）
        List<List<Integer>> ansII = combinationSumII(candidates, target);

        // 輸出 II 版
        printAns(ansII);
    }

    // ===== Combination Sum I: can reuse the same number unlimited times =====
    static List<List<Integer>> combinationSumI(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackI(nums, target, 0, new ArrayList<>(), res);
        return res;
    }

    static void backtrackI(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int v = nums[i];
            if (v > remain) break;            // 剪枝：因為已排序
            path.add(v);
            backtrackI(nums, remain - v, i, path, res); // i（可重複使用）
            path.remove(path.size() - 1);
        }
    }

    // ===== Combination Sum II: each number can be used at most once; skip duplicates =====
    static List<List<Integer>> combinationSumII(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackII(nums, target, 0, new ArrayList<>(), res);
        return res;
    }

    static void backtrackII(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 同層跳過重複
            int v = nums[i];
            if (v > remain) break;             // 剪枝
            path.add(v);
            backtrackII(nums, remain - v, i + 1, path, res); // i+1（每數最多一次）
            path.remove(path.size() - 1);
        }
    }

    // ===== Output helpers =====
    static void printAns(List<List<Integer>> ans) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> comb : ans) {
            sb.setLength(0);
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(comb.get(i));
            }
            System.out.println(sb.toString());
        }
    }

    // ===== Fast input =====
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
