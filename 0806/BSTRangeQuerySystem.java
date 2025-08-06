
import java.util.*;

public class BSTRangeQuerySystem {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static Node insert(Node root, int v) {
        if (root == null) {
            return new Node(v);
        }
        if (v < root.val) {
            root.left = insert(root.left, v);
        } else {
            root.right = insert(root.right, v);
        }
        return root;
    }

    // 1. range query -> list
    public static void rangeQuery(Node root, int low, int high, List<Integer> out) {
        if (root == null) {
            return;
        }
        if (root.val > low) {
            rangeQuery(root.left, low, high, out);
        }
        if (root.val >= low && root.val <= high) {
            out.add(root.val);
        }
        if (root.val < high) {
            rangeQuery(root.right, low, high, out);
        }
    }

    // 2. range count
    public static int rangeCount(Node root, int low, int high) {
        List<Integer> temp = new ArrayList<>();
        rangeQuery(root, low, high, temp);
        return temp.size();
    }

    // 3. range sum
    public static int rangeSum(Node root, int low, int high) {
        List<Integer> temp = new ArrayList<>();
        rangeQuery(root, low, high, temp);
        int s = 0;
        for (int x : temp) {
            s += x;

        }
        return s;
    }

    // 4. closest
    public static int closest(Node root, int target) {
        int best = root.val;
        Node cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(best - target)) {
                best = cur.val;
            }
            if (target < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] vals = {8, 4, 12, 2, 6, 10, 14};
        Node root = null;
        for (int v : vals) {
            root = insert(root, v);
        }

        List<Integer> inRange = new ArrayList<>();
        rangeQuery(root, 5, 11, inRange);
        System.out.println("Range [5,11]: " + inRange);
        System.out.println("Range count [5,11]: " + rangeCount(root, 5, 11));
        System.out.println("Range sum [5,11]: " + rangeSum(root, 5, 11));
        System.out.println("Closest to 9: " + closest(root, 9));
        System.out.println("Closest to 13: " + closest(root, 13));
    }
}
