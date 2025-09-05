// file name: LC21_MergeTwoLists_Clinics.java
import java.io.*;

public class LC21_MergeTwoLists_Clinics {

    /* 單向鏈結節點 */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        // 讀取 n, m
        Integer nObj = fs.nextIntNullable();
        if (nObj == null) return; // 沒有輸入
        int n = nObj;
        int m = fs.nextInt();

        // 讀取兩串升序整數
        ListNode a = buildList(fs, n);
        ListNode b = buildList(fs, m);

        // 合併（兩指針，Dummy + tail）
        ListNode merged = mergeTwoLists(a, b);

        // 輸出合併後序列（空白分隔）
        printList(merged, out);
        System.out.print(out.toString());
    }

    /* 迭代合併：每次挑較小節點接續；最後掛上剩餘串
       時間複雜度：O(n+m)；空間複雜度：O(1)（就地重連） */
    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // 其中一條可能尚有剩餘
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /* 依照讀入順序建立鏈結串列（保持升序） */
    static ListNode buildList(FastScanner fs, int len) throws IOException {
        ListNode head = null, tail = null;
        for (int i = 0; i < len; i++) {
            int v = fs.nextInt();
            ListNode node = new ListNode(v);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    /* 以空白分隔輸出整串節點值 */
    static void printList(ListNode head, StringBuilder out) {
        ListNode cur = head;
        boolean first = true;
        while (cur != null) {
            if (!first) out.append(' ');
            out.append(cur.val);
            first = false;
            cur = cur.next;
        }
        if (!first) out.append('\n'); // 若非空串，結尾換行
    }

    /* 快速讀取：忽略換行，連續讀整數 */
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
            // 跳過空白
            do {
                c = read();
                if (c == -1) return null;
            } while (c <= ' ');

            // 處理正負號
            if (c == '-') {
                sign = -1;
                c = read();
            }
            // 讀數字
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
