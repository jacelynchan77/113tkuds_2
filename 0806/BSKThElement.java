
import java.util.*;

public class BSKThElement {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static Node insert(Node r, int v) {
        if (r == null) {
            return new Node(v);
        }
        if (v < r.val) {
            r.left = insert(r.left, v);
        } else {
            r.right = insert(r.right, v);
        }
        return r;
    }

    // inorder to list
    public static void inorder(Node r, List<Integer> out) {
        if (r == null) {
            return;
        }
        inorder(r.left, out);
        out.add(r.val);
        inorder(r.right, out);
    }

    // 1. kth smallest
    public static Integer kthSmallest(Node r, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        if (k < 1 || k > list.size()) {
            return null;
        }
        return list.get(k - 1);
    }

    // 2. kth largest
    public static Integer kthLargest(Node r, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        if (k < 1 || k > list.size()) {
            return null;
        }
        return list.get(list.size() - k);
    }

    // 3. k..j elements
    public static List<Integer> rangeKtoJ(Node r, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(r, list);
        if (k < 1) {
            k = 1;
        }
        if (j > list.size()) {
            j = list.size();
        }
        if (k > j) {
            return new ArrayList<>();
        }
        return list.subList(k - 1, j);
    }

    // 4. dynamic k-th via maintaining sorted list (simple approach)
    static class DynamicK {

        ArrayList<Integer> list = new ArrayList<>();

        void insert(int v) {
            int i = Collections.binarySearch(list, v);
            if (i < 0) {
                i = -i - 1;

            }
            list.add(i, v);
        }

        void delete(int v) {
            int i = Collections.binarySearch(list, v);
            if (i >= 0) {
                list.remove(i);

            }
        }

        Integer kth(int k) {
            if (k < 1 || k > list.size()) {
                return null;

            }
            return list.get(k - 1);
        }
    }

    public static void main(String[] args) {
        int[] vals = {8, 4, 12, 2, 6, 10, 14};
        Node root = null;
        for (int v : vals) {
            root = insert(root, v);
        }

        System.out.println("3rd smallest: " + kthSmallest(root, 3));
        System.out.println("2nd largest: " + kthLargest(root, 2));
        System.out.println("k=2 to j=4: " + rangeKtoJ(root, 2, 4));

        DynamicK dk = new DynamicK();
        for (int v : vals) {
            dk.insert(v);
        }
        System.out.println("Dynamic 3rd: " + dk.kth(3));
        dk.insert(5);
        System.out.println("After insert 5, dynamic 3rd: " + dk.kth(3));
        dk.delete(4);
        System.out.println("After delete 4, dynamic 3rd: " + dk.kth(3));
    }
}
