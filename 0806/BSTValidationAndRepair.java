
import java.util.*;

public class BSTValidationAndRepair {

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

    // 1. validate BST
    public static boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(Node n, long min, long max) {
        if (n == null) {
            return true;
        }
        if (n.val <= min || n.val >= max) {
            return false;
        }
        return isValidBST(n.left, min, n.val) && isValidBST(n.right, n.val, max);
    }

    // 2. find invalid nodes (collect nodes violating inorder ascending)
    public static List<Node> findViolations(Node root) {
        List<Node> res = new ArrayList<>();
        List<Node> inorder = new ArrayList<>();
        collectInorder(root, inorder);
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i - 1).val > inorder.get(i).val) {
                res.add(inorder.get(i - 1));
                res.add(inorder.get(i));
            }
        }
        return res;
    }

    private static void collectInorder(Node n, List<Node> out) {
        if (n == null) {
            return;
        }
        collectInorder(n.left, out);
        out.add(n);
        collectInorder(n.right, out);
    }

    // 3. recover tree with two swapped nodes
    public static void recoverTree(Node root) {
        List<Node> inorder = new ArrayList<>();
        collectInorder(root, inorder);
        Node x = null, y = null;
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i - 1).val > inorder.get(i).val) {
                if (x == null) {
                    x = inorder.get(i - 1);
                }
                y = inorder.get(i);
            }
        }
        if (x != null && y != null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    // 4. min removals to make BST valid -> n - LIS length on inorder values
    public static int removalsToMakeValid(Node root) {
        List<Integer> vals = new ArrayList<>();
        collectInorderVals(root, vals);
        int lis = lengthOfLIS(vals);
        return vals.size() - lis;
    }

    private static void collectInorderVals(Node n, List<Integer> out) {
        if (n == null) {
            return;
        }
        collectInorderVals(n.left, out);
        out.add(n.val);
        collectInorderVals(n.right, out);
    }

    private static int lengthOfLIS(List<Integer> a) {
        ArrayList<Integer> tails = new ArrayList<>();
        for (int x : a) {
            int i = Collections.binarySearch(tails, x);
            if (i < 0) {
                i = -i - 1;
            }
            if (i == tails.size()) {
                tails.add(x);
            } else {
                tails.set(i, x);
            }
        }
        return tails.size();
    }

    public static void main(String[] args) {
        // Make BST then swap two nodes to simulate invalid BST
        int[] vals = {8, 4, 12, 2, 6, 10, 14};
        Node root = null;
        for (int v : vals) {
            root = insert(root, v);
        }
        // swap values 6 and 12 to corrupt
        Node a = root.left.right;
        Node b = root.right;
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;

        System.out.println("Valid before recover? " + isValidBST(root));
        List<Node> viol = findViolations(root);
        System.out.print("Violations found (values): ");
        for (Node n : viol) {
            System.out.print(n.val + " ");
        }
        System.out.println();

        recoverTree(root);
        System.out.println("Valid after recover? " + isValidBST(root));
        System.out.println("Removals to make valid (if not fixed): " + removalsToMakeValid(root));
    }
}
