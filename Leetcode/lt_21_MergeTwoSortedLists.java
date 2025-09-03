// file: lt_21_MergeTwoSortedLists.java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;

        return dummy.next;
    }
}

public class lt_21_MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        Solution sol = new Solution();
        ListNode merged = sol.mergeTwoLists(list1, list2);

        while (merged != null) {
            System.out.print(merged.val + (merged.next != null ? "->" : ""));
            merged = merged.next;
        }
    }
}
/*
解題思路：
1) 題目給定兩個已排序的鏈結串列，要求合併成一個新的排序鏈表。
2) 方法：使用「虛擬頭節點 dummy」輔助，避免處理邊界條件。
   - 準備指標 cur 從 dummy 出發。
   - 每次比較 list1.val 和 list2.val，把較小的節點接到 cur 後面，並推進該鏈表指標。
   - cur 也往後移動。
3) 當其中一個鏈表走完，直接把另一個剩餘部分接上即可（因為已經排序）。
4) 最後回傳 dummy.next 即是合併後的鏈表頭。
時間複雜度：O(m+n)，m 與 n 分別為兩鏈表長度。
空間複雜度：O(1)，僅使用常數額外指標。
*/