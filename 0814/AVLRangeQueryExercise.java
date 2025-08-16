// File: AVLRangeQueryExercise.java

import java.util.*;

public class AVLRangeQueryExercise {

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

    // Rotations
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // AVL Tree class
    static class AVLTree {

        private AVLNode root;

        // Insert node
        public void insert(int data) {
            root = insertNode(root, data);
        }

        private AVLNode insertNode(AVLNode node, int data) {
            if (node == null) {
                return new AVLNode(data);
            }

            if (data < node.data) {
                node.left = insertNode(node.left, data); 
            }else if (data > node.data) {
                node.right = insertNode(node.right, data); 
            }else {
                return node; // ignore duplicates
            }
            node.updateHeight();
            return balanceNode(node, data);
        }

        private AVLNode balanceNode(AVLNode node, int data) {
            int balance = node.getBalance();

            // Left Left
            if (balance > 1 && data < node.left.data) {
                return rightRotate(node);
            }
            // Right Right
            if (balance < -1 && data > node.right.data) {
                return leftRotate(node);
            }
            // Left Right
            if (balance > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // Right Left
            if (balance < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        // In-order traversal
        public void printTree() {
            printInOrder(root);
            System.out.println();
        }

        private void printInOrder(AVLNode node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.data + "(" + node.getBalance() + ") ");
                printInOrder(node.right);
            }
        }

        // Range query: find all elements between min and max (inclusive)
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        private void rangeQueryHelper(AVLNode node, int min, int max, List<Integer> result) {
            if (node == null) {
                return;
            }

            // Prune left subtree if current node is smaller than min
            if (node.data > min) {
                rangeQueryHelper(node.left, min, max, result);
            }

            // Include current node if within range
            if (node.data >= min && node.data <= max) {
                result.add(node.data);
            }

            // Prune right subtree if current node is larger than max
            if (node.data < max) {
                rangeQueryHelper(node.right, min, max, result);
            }
        }
    }

    // Main method: test range query
    public static void main(String[] args) {
        System.out.println("=== AVL Range Query Exercise ===");

        AVLTree tree = new AVLTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            tree.insert(val);
        }

        System.out.print("AVL Tree (in-order): ");
        tree.printTree();

        int min = 30, max = 65;
        List<Integer> rangeResult = tree.rangeQuery(min, max);
        System.out.println("Elements in range [" + min + ", " + max + "]: " + rangeResult);
    }
}
