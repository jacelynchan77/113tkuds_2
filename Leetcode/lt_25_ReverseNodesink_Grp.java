// file: lt_25_ReverseNodesink-Group.java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;

            ListNode groupNext = kth.next;

            ListNode prev = groupNext, cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }

            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }
        return dummy.next;
    }

    private ListNode getKth(ListNode cur, int k) {
        while (cur != null && k > 0) {
            cur = cur.next;
            k--;
        }
        return cur;
    }
}

public class lt_25_ReverseNodesink_Grp {
    public static void main(String[] args) {
        // 測試案例 1: [1,2,3,4,5], k=2 → [2,1,4,3,5]
        ListNode head1 = new ListNode(1,
                          new ListNode(2,
                          new ListNode(3,
                          new ListNode(4,
                          new ListNode(5)))));
        Solution sol = new Solution();
        ListNode ans1 = sol.reverseKGroup(head1, 2);
        printList(ans1);

        // 測試案例 2: [1,2,3,4,5], k=3 → [3,2,1,4,5]
        ListNode head2 = new ListNode(1,
                          new ListNode(2,
                          new ListNode(3,
                          new ListNode(4,
                          new ListNode(5)))));
        ListNode ans2 = sol.reverseKGroup(head2, 3);
        printList(ans2);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }
}

/*
解題邏輯與思路：
1) 題目要求每 k 個節點為一組進行翻轉；若最後不足 k，保持原樣。
2) 使用 dummy 節點簡化操作，groupPrev 指向當前分組的前一個節點。
3) 每次透過 getKth 找到第 k 個節點 kth 作為分組結尾，若找不到代表剩餘不足 k，結束。
4) 將當前分組 [groupPrev.next .. kth] 反轉，反轉時保存 groupNext (kth.next) 作為下一組的起點。
5) 翻轉完成後，連接 groupPrev.next = kth，並更新 groupPrev 到分組新的尾端（翻轉前的頭）。
6) 重複直到遍歷完成。
時間複雜度：O(n)，每個節點訪問一次。
空間複雜度：O(1)，僅使用常數額外指標。
*/
