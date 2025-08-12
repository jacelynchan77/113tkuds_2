
import java.util.*;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node(" + val + ")";
    }
}

public class MergeKSortedLists {

    // Main merge algorithm using Min Heap
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min Heap sorted by node value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        System.out.println("=== Merging Process ===");

        // Add the first node of each list into the heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]);
                System.out.println("Added head node " + lists[i].val + " from list " + i + " into the heap");
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int step = 1;

        while (!minHeap.isEmpty()) {
            // Extract the smallest node
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            System.out.println("Step " + step + ": Selected node " + node.val);

            // If this node has a next node, add it to the heap
            if (node.next != null) {
                minHeap.offer(node.next);
                System.out.println("  Added next node " + node.next.val + " into the heap");
            }

            // Show current heap state
            if (!minHeap.isEmpty()) {
                List<Integer> heapValues = new ArrayList<>();
                for (ListNode n : minHeap) {
                    heapValues.add(n.val);
                }
                System.out.println("  Current heap: " + heapValues);
            }

            step++;
        }

        return dummy.next;
    }

    // Method 2: Divide and Conquer merge (alternative solution)
    public static ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        return divideAndMerge(lists, 0, lists.length - 1);
    }

    private static ListNode divideAndMerge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return mergeTwoLists(lists[start], lists[end]);
        }

        int mid = start + (end - start) / 2;
        ListNode left = divideAndMerge(lists, start, mid);
        ListNode right = divideAndMerge(lists, mid + 1, end);

        return mergeTwoLists(left, right);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Helper to create linked list from array
    public static ListNode createList(int[] values) {
        if (values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    // Print linked list with label
    public static void printList(String label, ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        System.out.println(label + ": " + values);
    }

    public static void main(String[] args) {
        System.out.println("=== Merge K Sorted Linked Lists ===");

        // Create test lists
        ListNode list1 = createList(new int[]{1, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode list3 = createList(new int[]{2, 6});

        printList("List 1", list1);
        printList("List 2", list2);
        printList("List 3", list3);

        System.out.println("\nMethod 1: Using Min Heap");
        ListNode[] lists1 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged1 = mergeKLists(lists1);
        printList("Merged result", merged1);

        System.out.println("\nMethod 2: Divide and Conquer");
        ListNode[] lists2 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged2 = mergeKListsDivideConquer(lists2);
        printList("Merged result", merged2);

        System.out.println("\nComplexity comparison:");
        System.out.println("Min Heap method: Time O(n log k), Space O(k)");
        System.out.println("Divide and Conquer: Time O(n log k), Space O(log k)");
        System.out.println("Where n is total number of nodes, k is the number of lists");
    }
}
