// file: lt_19_RemoveNthNodeFromEndofList.java

// 鏈結串列節點定義
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n+1 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 與 slow 同步移動，直到 fast 到尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除目標節點
        slow.next = slow.next.next;

        return dummy.next;
    }
}

public class lt_19_RemoveNthNodeFromEndofList {
    public static void main(String[] args) {
        // 測試鏈表 [1,2,3,4,5], n=2
        ListNode head = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(4,
                        new ListNode(5)))));

        Solution sol = new Solution();
        ListNode res = sol.removeNthFromEnd(head, 2);

        // 輸出結果 [1,2,3,5]
        while (res != null) {
            System.out.print(res.val + (res.next != null ? "->" : ""));
            res = res.next;
        }
    }
}

/*
解題思路：
1. 建立 dummy 節點指向 head，方便處理刪除頭節點的情況。
2. 設定 fast 與 slow 指標都指向 dummy。
3. 先讓 fast 前進 n+1 步，確保 fast 與 slow 相距 n 步。
4. 之後 fast 與 slow 一起移動，直到 fast 抵達鏈尾。
5. 此時 slow 剛好在「待刪除節點的前一個節點」，修改 slow.next 即可刪除。
6. 回傳 dummy.next 作為新鏈表的頭節點。
時間複雜度 O(sz)，空間 O(1)。
*/
