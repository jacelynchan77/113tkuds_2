// File: AVLNode.java
public class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    // Constructor
    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    // Calculate balance factor
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    // Update node height
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // Right rotation
    public static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // Left rotation
    public static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // Insert node into AVL tree
    public static AVLNode insert(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) node.left = insert(node.left, data);
        else if (data > node.data) node.right = insert(node.right, data);
        else return node; // No duplicates

        node.updateHeight();
        int balance = node.getBalance();

        // Balance the tree
        if (balance > 1 && data < node.left.data) return rotateRight(node);           // Left Left
        if (balance < -1 && data > node.right.data) return rotateLeft(node);          // Right Right
        if (balance > 1 && data > node.left.data) {                                   // Left Right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && data < node.right.data) {                                  // Right Left
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // In-order traversal
    public static void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            inorder(node.right);
        }
    }

    // Main method
    public static void main(String[] args) {
        AVLNode root = null;

        System.out.println("=== AVL Tree Test ===");

        int[] values = {10, 20, 30, 40, 50, 25};
        System.out.println("Inserting nodes: 10, 20, 30, 40, 50, 25");

        for (int v : values) {
            root = insert(root, v);
        }

        System.out.println("\nIn-order traversal (value(balance factor)):");
        inorder(root);
        System.out.println("\nRoot node: " + root.data + ", height: " + root.height);
    }
}
