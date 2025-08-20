import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {

    // Heap 中的節點: (timeInMinutes, whichList, indexInThatList)
    static class Node {
        int t;      // 從 00:00 起算的分鐘數
        int li;     // 第幾個清單
        int idx;    // 該清單中的索引
        Node(int t, int li, int idx) { this.t = t; this.li = li; this.idx = idx; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(readNonEmptyLine(br).trim());

        List<List<Integer>> lists = new ArrayList<>(K);
        boolean sawHHmm = false; // 若輸入中有冒號，輸出就以 HH:mm 格式

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(readNonEmptyLine(br).trim());
            List<Integer> cur = new ArrayList<>(len);
            while (cur.size() < len) {
                String line = readNonEmptyLine(br);
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens() && cur.size() < len) {
                    String tok = st.nextToken();
                    if (tok.indexOf(':') >= 0) sawHHmm = true;
                    cur.add(parseTimeToMinutes(tok));
                }
            }
            lists.add(cur);
        }

        List<Integer> merged = mergeKSorted(lists);

        // 輸出結果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(sawHHmm ? toHHmm(merged.get(i)) : merged.get(i));
        }
        System.out.println(sb.toString());
    }

    // 使用最小堆合併 K 個已排序清單
    /*
     * 時間複雜度: O(T log K)，其中 T 為所有清單中元素總數。
     * 空間複雜度: O(K)，用於最小堆（不含輸入儲存）。
     * 說明：
     * 1. 每次從堆中取出最小元素，並將下一個候選加入堆。
     * 2. 每次堆操作為 O(log K)，共執行 T 次。
     * 3. 總體為 O(T log K)。
     */
    private static List<Integer> mergeKSorted(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.t));
        int K = lists.size();

        // 將每個清單的第一個元素放入堆中
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> out = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            out.add(cur.t);

            int nextIdx = cur.idx + 1;
            if (nextIdx < lists.get(cur.li).size()) {
                pq.offer(new Node(lists.get(cur.li).get(nextIdx), cur.li, nextIdx));
            }
        }
        return out;
    }

    // 輔助函數: 讀取下一個非空行
    private static String readNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() > 0) return line;
        }
        return ""; // 合法輸入下不會發生
    }

    // 將輸入轉為分鐘數: 可以是整數或 HH:mm 格式
    private static int parseTimeToMinutes(String s) {
        if (s.indexOf(':') < 0) {
            return Integer.parseInt(s); // 純分鐘數
        }
        String[] parts = s.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    // 將分鐘數轉回 HH:mm 格式（補零）
    private static String toHHmm(int mins) {
        int h = mins / 60;
        int m = mins % 60;
        return String.format("%02d:%02d", h, m);
    }
}
