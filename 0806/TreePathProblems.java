
import java.util.*;

public class TreePathProblems {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static Node sample() {
        Node r = new Node(5);
        r.left = new Node(4);
        r.right = new Node(8);
        r.left.left = new Node(11);
        r.left.left.left = new Node(7);
        r.left.left.right = new Node(2);
        r.right.left = new Node(13);
        r.right.right = new Node(4);
        r.right.right.right = new Node(1);
        return r;
    }

    // 1. all root-to-leaf paths
    public static List<List<Integer>> allPaths(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(Node n, List<Integer> cur, List<List<Integer>> res) {
        if (n == null) {
            return;
        }
        cur.add(n.val);
        if (n.left == null && n.right == null) {
            res.add(new ArrayList<>(cur));
        } else {
            dfs(n.left, cur, res);
            dfs(n.right, cur, res);
        }
        cur.remove(cur.size() - 1);
    }

    // 2. has root-to-leaf sum
    public static boolean hasPathSum(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    // 3. max root-to-leaf sum
    public static int maxRootToLeafSum(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return root.val + Math.max(
                root.left == null ? Integer.MIN_VALUE : maxRootToLeafSum(root.left),
                root.right == null ? Integer.MIN_VALUE : maxRootToLeafSum(root.right)
        );
    }

    // 4. diameter
    static int diameter;

    public static int treeDiameter(Node root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private static int height(Node n) {
        if (n == null) {
            return 0;
        }
        int lh = height(n.left);
        int rh = height(n.right);
        diameter = Math.max(diameter, lh + rh);
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Node r = sample();
        System.out.println("All root-to-leaf paths: " + allPaths(r));
        System.out.println("Has path sum 22? " + hasPathSum(r, 22));
        System.out.println("Max root-to-leaf sum: " + maxRootToLeafSum(r));
        System.out.println("Tree diameter: " + treeDiameter(r));
    }
}
