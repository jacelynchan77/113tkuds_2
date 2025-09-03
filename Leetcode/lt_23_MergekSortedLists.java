// file: lt_23_MergekSortedLists.java
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode h : lists) {
            if (h != null) pq.offer(h);
        }

        ListNode dummy = new ListNode(-1), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }
}

public class lt_23_MergekSortedLists {
    public static void main(String[] args) {
        ListNode a = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode b = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode c = new ListNode(2, new ListNode(6));
        ListNode[] lists = new ListNode[]{a, b, c};

        ListNode ans = new Solution().mergeKLists(lists);
        while (ans != null) {
            System.out.print(ans.val + (ans.next != null ? "->" : ""));
            ans = ans.next;
        }
        // Expected output: 1->1->2->3->4->4->5->6
    }
}
