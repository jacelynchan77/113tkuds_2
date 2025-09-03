// file: lt_24_SwapNodesinPairs.java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            first.next = second.next;
            second.next = first;
            cur.next = second;

            cur = first;
        }
        return dummy.next;
    }
}

public class lt_24_SwapNodesinPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(4))));

        Solution sol = new Solution();
        ListNode ans = sol.swapPairs(head);

        while (ans != null) {
            System.out.print(ans.val + (ans.next != null ? "->" : ""));
            ans = ans.next;
        }
        
    }
}
/*
解題邏輯與思路：
1) 題目要求交換相鄰兩個節點，不能單純交換數值。
2) 使用 dummy 節點簡化操作，cur 指向每次交換區塊的前一個節點。
3) 在迴圈中，定義 first=cur.next、second=cur.next.next，進行指標交換：
   - first.next = second.next
   - second.next = first
   - cur.next = second
   如此即可完成兩兩對調。
4) 完成後將 cur 往後移動到 first（即已交換到第二個位置的節點），繼續處理下一對。
5) 當 cur.next==null 或 cur.next.next==null，代表不足一對，交換結束。
時間複雜度：O(n)，每個節點最多訪問一次。
空間複雜度：O(1)，僅用到常數額外變數。
*/