// File: AVLRotationExercise.java

public class AVLRotationExercise {

    // Node class
    static class AVLNode {

        int data;
        AVLNode left, right;
        int height;

        AVLNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }

        void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }

        int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }
    }

    // Right rotation
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x; // new root
    }

    // Left rotation
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y; // new root
    }

    // Left-Right rotation
    public static AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // Right-Left rotation
    public static AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // In-order traversal
    public static void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== AVL Rotation Exercise ===");

        // Test Right Rotation (Left-Left case)
        AVLNode root1 = new AVLNode(30);
        root1.left = new AVLNode(20);
        root1.left.left = new AVLNode(10);
        root1.updateHeight();
        root1.left.updateHeight();
        root1 = rightRotate(root1);
        System.out.print("Right Rotation (LL case): ");
        printInOrder(root1);
        System.out.println();

        // Test Left Rotation (Right-Right case)
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);
        root2.updateHeight();
        root2.right.updateHeight();
        root2 = leftRotate(root2);
        System.out.print("Left Rotation (RR case): ");
        printInOrder(root2);
        System.out.println();

        // Test Left-Right Rotation (Left-Right case)
        AVLNode root3 = new AVLNode(30);
        root3.left = new AVLNode(10);
        root3.left.right = new AVLNode(20);
        root3.updateHeight();
        root3.left.updateHeight();
        root3 = leftRightRotate(root3);
        System.out.print("Left-Right Rotation (LR case): ");
        printInOrder(root3);
        System.out.println();

        // Test Right-Left Rotation (Right-Left case)
        AVLNode root4 = new AVLNode(10);
        root4.right = new AVLNode(30);
        root4.right.left = new AVLNode(20);
        root4.updateHeight();
        root4.right.updateHeight();
        root4 = rightLeftRotate(root4);
        System.out.print("Right-Left Rotation (RL case): ");
        printInOrder(root4);
        System.out.println();
    }
}
