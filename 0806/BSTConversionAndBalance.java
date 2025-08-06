
import java.util.*;

public class BSTConversionAndBalance {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    // 1. BST -> sorted doubly linked list (in-place)
    static Node head = null, prev = null;

    public static Node bstToDoublyList(Node root) {
        head = prev = null;
        convert(root);
        return head;
    }

    private static void convert(Node cur) {
        if (cur == null) {
            return;
        }
        convert(cur.left);
        if (prev == null) {
            head = cur;
        } else {
            prev.right = cur;
            cur.left = prev;
        }
        prev = cur;
        convert(cur.right);
    }

    // 2. sorted array -> balanced BST
    public static Node sortedArrayToBST(int[] a, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        Node n = new Node(a[mid]);
        n.left = sortedArrayToBST(a, l, mid - 1);
        n.right = sortedArrayToBST(a, mid + 1, r);
        return n;
    }

    // 3. check balanced and compute balance factor (height left - right at root)
    public static int height(Node n) {
        if (n == null) {
            return 0;

        }
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public static boolean isBalanced(Node n) {
        if (n == null) {
            return true;
        }
        int lh = height(n.left), rh = height(n.right);
        if (Math.abs(lh - rh) > 1) {
            return false;
        }
        return isBalanced(n.left) && isBalanced(n.right);
    }

    public static int balanceFactor(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // 4. convert to greater-sum tree (each node becomes sum of >= node values)
    static int acc = 0;

    public static void greaterSumTree(Node root) {
        acc = 0;
        revInorder(root);
    }

    private static void revInorder(Node n) {
        if (n == null) {
            return;
        }
        revInorder(n.right);
        acc += n.val;
        n.val = acc;
        revInorder(n.left);
    }

    // helpers
    public static void inorder(Node n, List<Integer> out) {
        if (n == null) {
            return;

        }
        inorder(n.left, out);
        out.add(n.val);
        inorder(n.right, out);
    }

    public static void main(String[] args) {
        // sample BST
        Node root = null;
        int[] vals = {2, 1, 3, 4, 6};
        for (int v : vals) {
            root = insert(root, v);
        }

        Node dll = bstToDoublyList(root);
        System.out.print("Doubly list forward: ");
        Node cur = dll;
        while (cur != null) {
            System.out.print(cur.val + (cur.right != null ? " -> " : ""));
            cur = cur.right;
        }
        System.out.println();

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        Node balanced = sortedArrayToBST(sorted, 0, sorted.length - 1);
        System.out.println("Balanced inorder: " + inorderList(balanced));

        System.out.println("Is balanced? " + isBalanced(balanced) + ", balance factor at root: " + balanceFactor(balanced));

        // greater sum
        Node groot = insert(null, 4);
        insert(groot, 1);
        insert(groot, 6);
        insert(groot, 0);
        insert(groot, 2);
        greaterSumTree(groot);
        System.out.println("Greater-sum tree inorder: " + inorderList(groot));
    }

    private static Node insert(Node r, int v) {
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

    private static List<Integer> inorderList(Node r) {
        List<Integer> a = new ArrayList<>();
        inorder(r, a);
        return a;
    }
}
