import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {

    // Record for (score, original index)
    static class Node {
        int score, idx;
        Node(int s, int i) { score = s; idx = i; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            int s = Integer.parseInt(st.nextToken());
            a[i] = new Node(s, i);
        }

        /*
         * 時間複雜度：O(n)
         * 說明：
         * 1) 由後往前對每個內部節點做 siftDown，總成本為 O(n)。
         * 2) 每個元素的「向下調整」次數受樹高限制，整體為線性。
         */
        buildMaxHeap(a);

        /*
         * 時間複雜度：O(n log n)
         * 說明：
         * 1) 進行 n 次「取最大並放到尾端」的交換。
         * 2) 每次交換後做一次 siftDown，成本 O(log n)。
         * 3) 總計 O(n log n)。
         */
        heapSortAscending(a);

        // Output scores in ascending order
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i].score);
        }
        System.out.println(sb.toString());
    }

    // Comparator for Max-Heap:
    // return true if x should be above y in a Max-Heap.
    // 規則：分數高者較大；分數相同時，索引較大者較大（使最後輸出時較小索引先出現）。
    private static boolean greater(Node x, Node y) {
        if (x.score != y.score) return x.score > y.score;
        return x.idx > y.idx; // tie-break: larger index considered larger
    }

    private static void swap(Node[] a, int i, int j) {
        Node t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void buildMaxHeap(Node[] a) {
        int n = a.length;
        for (int i = (n >>> 1) - 1; i >= 0; i--) {
            siftDown(a, i, n);
        }
    }

    /*
     * 時間複雜度：O(log n)
     * 說明：
     * 1) 沿著單一路徑向下調整，最多下降樹高 h ≈ ⌊log2 n⌋ 層。
     * 2) 每層僅常數次比較與交換。
     */
    private static void siftDown(Node[] a, int i, int size) {
        while (true) {
            int left = (i << 1) + 1;
            if (left >= size) break;
            int right = left + 1;
            int largest = left;
            if (right < size && greater(a[right], a[left])) largest = right;
            if (greater(a[largest], a[i])) {
                swap(a, i, largest);
                i = largest;
            } else break;
        }
    }

    // Standard heapsort with Max-Heap → ascending array
    private static void heapSortAscending(Node[] a) {
        for (int end = a.length - 1; end > 0; end--) {
            swap(a, 0, end);          // 最大值放到尾端
            siftDown(a, 0, end);      // 對剩餘區間恢復 Max-Heap
        }
    }
}

