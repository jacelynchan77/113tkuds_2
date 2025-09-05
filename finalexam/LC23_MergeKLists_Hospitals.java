// file name: LC23_MergeKLists_Hospitals.java
import java.io.*;
import java.util.*;

/** 合併 k 條已排序單向鏈結串列（以 -1 為每行終止符），使用最小堆
 *  時間：O(N log k)，空間：O(k)
 */
public class LC23_MergeKLists_Hospitals {

    /* 單向鏈結節點 */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        Integer kObj = fs.nextIntNullable();
        if (kObj == null) return; // 無輸入
        int k = kObj;

        // 讀取 k 行：每行為升序整數，以 -1 結尾；可能為空（直接 -1）
        ListNode[] heads = new ListNode[k];
        for (int i = 0; i < k; i++) {
            heads[i] = readTerminatedList(fs);
        }

        // 合併
        ListNode merged = mergeKLists(heads);

        // 輸出合併後序列
        printList(merged, out);
        System.out.print(out.toString());
    }

    /** 使用最小堆合併 k 串 */
    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<NodeWrap> pq = new PriorityQueue<>(
            Comparator.<NodeWrap>comparingInt(w -> w.node.val)
                      .thenComparingInt(w -> w.idx) // 穩定次序的輔助鍵
        );
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) pq.offer(new NodeWrap(lists[i], i));
        }

        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            NodeWrap w = pq.poll();
            tail.next = w.node;
            tail = tail.next;
            if (w.node.next != null) pq.offer(new NodeWrap(w.node.next, w.idx));
        }
        return dummy.next;
    }

    static class NodeWrap {
        ListNode node;
        int idx; // 來源串列索引（作為 tie-breaker）
        NodeWrap(ListNode n, int i) { node = n; idx = i; }
    }

    /** 讀取一行「以 -1 結尾」的串列（允許空行列：直接 -1） */
    static ListNode readTerminatedList(FastScanner fs) throws IOException {
        ListNode head = null, tail = null;
        while (true) {
            int v = fs.nextInt();
            if (v == -1) break; // 行終止
            ListNode nd = new ListNode(v);
            if (head == null) head = tail = nd;
            else { tail.next = nd; tail = nd; }
        }
        return head;
    }

    /** 以空白分隔輸出 */
    static void printList(ListNode head, StringBuilder out) {
        boolean first = true;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (!first) out.append(' ');
            out.append(cur.val);
            first = false;
        }
        if (!first) out.append('\n'); // 若非空串列，結尾換行
    }

    /* 高效整數掃描器（忽略空白） */
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        Integer nextIntNullable() throws IOException {
            int c, sign = 1, val = 0;
            // skip spaces
            do {
                c = read();
                if (c == -1) return null;
            } while (c <= ' ');
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') {
                if (c < '0' || c > '9') throw new IOException("Invalid integer input");
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            Integer x = nextIntNullable();
            if (x == null) throw new EOFException();
            return x;
        }
    }
}
