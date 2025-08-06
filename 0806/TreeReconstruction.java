
import java.util.*;

public class TreeReconstruction {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    // 1. from preorder & inorder
    public static Node buildPreIn(int[] pre, int[] in) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idx.put(in[i], i);
        }
        return buildPreInHelper(pre, 0, pre.length - 1, in, 0, in.length - 1, idx);
    }

    private static Node buildPreInHelper(int[] pre, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> idx) {
        if (pl > pr) {
            return null;
        }
        int rootVal = pre[pl];
        int inRoot = idx.get(rootVal);
        Node root = new Node(rootVal);
        int leftSize = inRoot - il;
        root.left = buildPreInHelper(pre, pl + 1, pl + leftSize, in, il, inRoot - 1, idx);
        root.right = buildPreInHelper(pre, pl + leftSize + 1, pr, in, inRoot + 1, ir, idx);
        return root;
    }

    // 2. from postorder & inorder
    public static Node buildPostIn(int[] post, int[] in) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idx.put(in[i], i);
        }
        return buildPostInHelper(post, 0, post.length - 1, in, 0, in.length - 1, idx);
    }

    private static Node buildPostInHelper(int[] post, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> idx) {
        if (pl > pr) {
            return null;
        }
        int rootVal = post[pr];
        int inRoot = idx.get(rootVal);
        Node root = new Node(rootVal);
        int leftSize = inRoot - il;
        root.left = buildPostInHelper(post, pl, pl + leftSize - 1, in, il, inRoot - 1, idx);
        root.right = buildPostInHelper(post, pl + leftSize, pr - 1, in, inRoot + 1, ir, idx);
        return root;
    }

    // 3. from level-order to complete tree (given complete property)
    public static Node buildFromLevel(int[] level) {
        if (level.length == 0) {
            return null;
        }
        Node root = new Node(level[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < level.length) {
            Node cur = q.poll();
            cur.left = new Node(level[i++]);
            q.add(cur.left);
            if (i < level.length) {
                cur.right = new Node(level[i++]);
                q.add(cur.right);
            }
        }
        return root;
    }

    // 4. validate equality by comparing traversals
    public static List<Integer> inorder(Node r) {
        List<Integer> a = new ArrayList<>();
        inorderRec(r, a);
        return a;
    }

    private static void inorderRec(Node r, List<Integer> a) {
        if (r == null) {
            return;

        }
        inorderRec(r.left, a);
        a.add(r.val);
        inorderRec(r.right, a);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        int[] post = {4, 5, 2, 6, 7, 3, 1};
        Node a = buildPreIn(pre, in);
        Node b = buildPostIn(post, in);
        Node c = buildFromLevel(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Inorder of pre-in built: " + inorder(a));
        System.out.println("Inorder of post-in built: " + inorder(b));
        System.out.println("Inorder of level built: " + inorder(c));
        System.out.println("PreIn and PostIn equal? " + inorder(a).equals(inorder(b)));
    }
}
