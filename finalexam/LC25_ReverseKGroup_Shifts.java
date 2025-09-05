// file name: LC25_ReverseKGroup_Shifts.java
import java.io.*;

public class LC25_ReverseKGroup_Shifts {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // read k
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int k = Integer.parseInt(line.trim());

        // read sequence (second line)
        line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            return; // no numbers
        }
        String[] parts = line.trim().split("\\s+");

        ListNode head = null, tail = null;
        for (String p : parts) {
            int v = Integer.parseInt(p);
            ListNode nd = new ListNode(v);
            if (head == null) head = tail = nd;
            else { tail.next = nd; tail = nd; }
        }

        if (k <= 1 || head == null || head.next == null) {
            printList(head);
            return;
        }

        ListNode res = reverseKGroup(head, k);
        printList(res);
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode groupEnd = kthFrom(groupPrev, k);
            if (groupEnd == null) break;
            ListNode nextGroupHead = groupEnd.next;

            ListNode newHead = reverseSegment(groupPrev.next, groupEnd);
            ListNode newTail = groupPrev.next;

            groupPrev.next = newHead;
            newTail.next = nextGroupHead;
            groupPrev = newTail;
        }
        return dummy.next;
    }

    static ListNode kthFrom(ListNode start, int k) {
        ListNode cur = start;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
            if (cur == null) return null;
        }
        return cur;
    }

    static ListNode reverseSegment(ListNode start, ListNode end) {
        ListNode prev = end.next;
        ListNode cur = start;
        while (prev != end) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return end;
    }

    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (!first) sb.append(' ');
            sb.append(cur.val);
            first = false;
        }
        if (!first) sb.append('\n');
        System.out.print(sb.toString());
    }
}
