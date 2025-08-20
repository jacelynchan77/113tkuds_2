import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    public static void main(String[] args) throws IOException {
        // Force UTF-8 for input & output, regardless of console encoding
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // Min-Heap to keep top K items
        PriorityQueue<Item> heap = new PriorityQueue<>(k);

        /*
         * Time Complexity: O(n log k)
         * 說明：
         * 1. 共有 n 筆輸入，每筆可能要進行 heap 的 insert/poll。
         * 2. 每次 heap 操作成本為 O(log k)，因為 heap 大小最多為 k。
         * 3. 總計：O(n log k)。
         */
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());

            Item item = new Item(name, qty);

            if (heap.size() < k) {
                heap.offer(item); // O(log k)
            } else if (item.compareTo(heap.peek()) > 0) {
                heap.poll();      // O(log k)
                heap.offer(item); // O(log k)
            }
        }

        // Sort result in descending order
        List<Item> result = new ArrayList<>(heap);
        /*
         * Time Complexity: O(k log k)
         * 說明：
         * 1. 最多有 k 個元素需要排序。
         * 2. 排序成本 O(k log k)。
         */
        result.sort(Collections.reverseOrder());

        /*
         * Time Complexity: O(k)
         * 說明：
         * 1. 輸出結果最多 k 行。
         * 2. 因此輸出成本為 O(k)。
         */
        for (Item it : result) {
            out.println(it.name + " " + it.qty);
        }
    }
}

class Item implements Comparable<Item> {
    String name;
    int qty;

    Item(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    /*
     * Time Complexity: O(1)
     * 說明：
     * 1. 比較時僅進行整數比較與字串比較 (最壞 O(L)，L 為字串長度)。
     * 2. 本題品名長度有限，可視為 O(1)。
     */
    @Override
    public int compareTo(Item other) {
        if (this.qty != other.qty) {
            return Integer.compare(this.qty, other.qty);
        }
        return this.name.compareTo(other.name);
    }
}
