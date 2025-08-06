
public class TreeMirrorAndSymmetry {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public static Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node n = new Node(root.val);
        n.left = mirror(root.right);
        n.right = mirror(root.left);
        return n;
    }

    public static boolean areMirrors(Node a, Node b) {
        return isMirror(a, b);
    }

    public static boolean isSubtree(Node root, Node sub) {
        if (sub == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (isSame(root, sub)) {
            return true;
        }
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    private static boolean isSame(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    // sample and tests
    public static Node sample() {
        Node r = new Node(1);
        r.left = new Node(2);
        r.right = new Node(2);
        r.left.left = new Node(3);
        r.right.right = new Node(3);
        return r;
    }

    public static void main(String[] args) {
        Node r = sample();
        System.out.println("Is symmetric? " + isSymmetric(r));

        Node m = mirror(r);
        System.out.println("Mirror root left val: " + (m.left != null ? m.left.val : "null"));

        System.out.println("Are r and m mirrors? " + areMirrors(r, m));

        // subtree test
        Node sub = new Node(2);
        sub.left = new Node(3);
        System.out.println("Is sub a subtree? " + isSubtree(r, sub));
    }
}
