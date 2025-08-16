// File: PersistentAVLExercise.java

import java.util.*;

public class PersistentAVLExercise {

    static class AVLNode {

        int data;
        AVLNode left, right;
        int height;

        public AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }

        public int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Right rotation
    private static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // Left rotation
    private static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // Insert into persistent AVL (returns new root)
    public static AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        // Path-copying for persistence
        AVLNode newNode = new AVLNode(node.data);
        newNode.left = node.left;
        newNode.right = node.right;

        if (data < node.data) {
            newNode.left = insert(node.left, data); 
        }else if (data > node.data) {
            newNode.right = insert(node.right, data); 
        }else {
            return newNode; // no duplicates
        }
        newNode.updateHeight();
        int balance = newNode.getBalance();

        // Balance
        if (balance > 1 && data < newNode.left.data) {
            return rotateRight(newNode);
        }
        if (balance < -1 && data > newNode.right.data) {
            return rotateLeft(newNode);
        }
        if (balance > 1 && data > newNode.left.data) {
            newNode.left = rotateLeft(newNode.left);
            return rotateRight(newNode);
        }
        if (balance < -1 && data < newNode.right.data) {
            newNode.right = rotateRight(newNode.right);
            return rotateLeft(newNode);
        }

        return newNode;
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
        System.out.println("=== Persistent AVL Tree Test ===");

        List<AVLNode> versions = new ArrayList<>();
        AVLNode root = null;

        int[] values = {10, 20, 30, 40};
        for (int v : values) {
            root = insert(root, v);
            versions.add(root); // save version after each insertion
        }

        // Print all versions
        for (int i = 0; i < versions.size(); i++) {
            System.out.print("Version " + (i + 1) + ": ");
            inorder(versions.get(i));
            System.out.println();
        }
    }
}
