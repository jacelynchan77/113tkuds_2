
import java.util.*;

public class BinaryTreeBasicOperations {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    // build sample tree:
    //         8
    //       /   \
    //      4     12
    //     / \   /  \
    //    2  6  10  14
    public static Node sample() {
        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.left = new Node(10);
        root.right.right = new Node(14);
        return root;
    }

    // 1. sum and average
    public static int sum(Node root) {
        if (root == null) {
            return 0;
        }
        return root.val + sum(root.left) + sum(root.right);
    }

    public static double average(Node root) {
        int s = sum(root);
        int n = count(root);
        return n == 0 ? 0 : (double) s / n;
    }

    public static int count(Node r) {
        if (r == null) {
            return 0;
        }
        return 1 + count(r.left) + count(r.right);
    }

    // 2. max and min node
    public static int max(Node r) {
        if (r == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(r.val, Math.max(max(r.left), max(r.right)));
    }

    public static int min(Node r) {
        if (r == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(r.val, Math.min(min(r.left), min(r.right)));
    }

    // 3. width (max nodes in any level)
    public static int width(Node r) {
        if (r == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(r);
        int maxw = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            maxw = Math.max(maxw, sz);
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
        }
        return maxw;
    }

    // 4. isComplete
    public static boolean isComplete(Node root) {
        if (root == null) {
            return true;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean seenNull = false;
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n == null) {
                seenNull = true;
                continue;
            }
            if (seenNull) {
                return false;
            }
            q.add(n.left);
            q.add(n.right);
        }
        return true;
    }

    public static void main(String[] args) {
        Node root = sample();
        System.out.println("Sum: " + sum(root));
        System.out.println("Average: " + String.format("%.2f", average(root)));
        System.out.println("Max: " + max(root) + ", Min: " + min(root));
        System.out.println("Width: " + width(root));
        System.out.println("Is complete? " + isComplete(root));
    }
}
