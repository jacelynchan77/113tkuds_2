// lt_02_AddTwoNumbers.java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}

public class lt_02_AddTwoNumbers {
    // Helper: build linked list from array
    static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // Helper: print list as string
    static String toStringList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        ListNode l1 = build(new int[]{2,4,3});
        ListNode l2 = build(new int[]{5,6,4});
        System.out.println(toStringList(sol.addTwoNumbers(l1, l2))); // [7,0,8]

        // Example 2
        ListNode l3 = build(new int[]{0});
        ListNode l4 = build(new int[]{0});
        System.out.println(toStringList(sol.addTwoNumbers(l3, l4))); // [0]

        // Example 3
        ListNode l5 = build(new int[]{9,9,9,9,9,9,9});
        ListNode l6 = build(new int[]{9,9,9,9});
        System.out.println(toStringList(sol.addTwoNumbers(l5, l6))); // [8,9,9,9,0,0,0,1]
    }
}
