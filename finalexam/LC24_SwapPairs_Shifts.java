// file name: LC24_SwapPairs_Shifts.java
import java.io.*;

/** 將鏈結串列中相鄰節點兩兩交換（班表成對輪班互換模擬）
 *  時間：O(n)，空間：O(1)
 *
 *  Input:
 *    一行整數序列（空白分隔）
 *  Example:
 *    1 2 3 4
 *  Output:
 *    2 1 4 3
 */
public class LC24_SwapPairs_Shifts {

    /* 單向鏈結節點 */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            return; // no input
        }

        String[] parts = line.trim().split("\\s+");
        ListNode head = null, tail = null;
        for (String p : parts) {
            int v = Integer.parseInt(p);
            ListNode nd = new ListNode(v);
            if (head == null) head = tail = nd;
            else { tail.next = nd; tail = nd; }
        }

        // 執行交換
        ListNode swapped = swapPairs(head);

        // 輸出
        printList(swapped);
    }

    /** 迴圈處理 (a,b) → prev.next=b, a.next=b.next, b.next=a */
    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // swap
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // 前進到下一對
            prev = a;
        }
        return dummy.next;
    }

    /** 空白分隔輸出整串 */
    static void printList(ListNode head) {
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (!first) out.append(' ');
            out.append(cur.val);
            first = false;
        }
        if (!first) out.append('\n');
        System.out.print(out.toString());
    }
}
