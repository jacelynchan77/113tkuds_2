
import java.util.*;

public class LevelOrderTraversalVariations {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static Node sample() {
        Node r = new Node(1);
        r.left = new Node(2);
        r.right = new Node(3);
        r.left.left = new Node(4);
        r.left.right = new Node(5);
        r.right.left = new Node(6);
        r.right.right = new Node(7);
        return r;
    }

    // 1. nodes per level lists
    public static List<List<Integer>> levelLists(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                level.add(n.val);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    // 2. zigzag
    public static List<List<Integer>> zigzag(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> dq = new LinkedList<>();
        dq.add(root);
        boolean leftToRight = true;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < sz; i++) {
                Node n = leftToRight ? dq.pollFirst() : dq.pollLast();
                if (leftToRight) {
                    level.addLast(n.val);
                    if (n.left != null) {
                        dq.addLast(n.left);
                    }
                    if (n.right != null) {
                        dq.addLast(n.right);
                    }
                } else {
                    level.addLast(n.val);
                    if (n.right != null) {
                        dq.addFirst(n.right);
                    }
                    if (n.left != null) {
                        dq.addFirst(n.left);
                    }
                }
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 3. last node of each level
    public static List<Integer> lastOfEachLevel(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                if (i == sz - 1) {
                    res.add(n.val);
                }
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
        }
        return res;
    }

    // 4. vertical order
    public static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        q.add(root);
        cols.add(0);
        while (!q.isEmpty()) {
            Node n = q.poll();
            int c = cols.poll();
            map.computeIfAbsent(c, k -> new ArrayList<>()).add(n.val);
            if (n.left != null) {
                q.add(n.left);
                cols.add(c - 1);
            }
            if (n.right != null) {
                q.add(n.right);
                cols.add(c + 1);
            }
        }
        for (List<Integer> l : map.values()) {
            res.add(l);
        }
        return res;
    }

    public static void main(String[] args) {
        Node r = sample();
        System.out.println("Level lists: " + levelLists(r));
        System.out.println("Zigzag: " + zigzag(r));
        System.out.println("Last of each level: " + lastOfEachLevel(r));
        System.out.println("Vertical order: " + verticalOrder(r));
    }
}
