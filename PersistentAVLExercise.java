// File: PersistentAVLExercise.java

import java.util.*;

public class PersistentAVLExercise {

    static class AVLNode {

        final int data;
        final AVLNode left, right;
        final int height;

        AVLNode(int data, AVLNode left, AVLNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = Math.max(height(left), height(right)) + 1;
        }

        private static int height(AVLNode node) {
            return (node == null) ? 0 : node.height;
        }

        int getBalance() {
            return height(left) - height(right);
        }
    }

    static class PersistentAVL {

        private final List<AVLNode> versions = new ArrayList<>();

        public void insert(int value) {
            AVLNode newRoot = insert(versions.isEmpty() ? null : versions.get(versions.size() - 1), value);
            versions.add(newRoot);
        }

        private AVLNode insert(AVLNode node, int value) {
            if (node == null) {
                return new AVLNode(value, null, null);
            }

            AVLNode newNode;
            if (value < node.data) {
                newNode = new AVLNode(node.data, insert(node.left, value), node.right);
            } else if (value > node.data) {
                newNode = new AVLNode(node.data, node.left, insert(node.right, value));
            } else {
                // Duplicate values not allowed, return original node
                return node;
            }

            return balance(newNode);
        }

        private AVLNode balance(AVLNode node) {
            int balance = node.getBalance();

            // Left heavy
            if (balance > 1) {
                if (node.left.getBalance() >= 0) {
                    return rightRotate(node);
                } else {
                    return leftRightRotate(node);
                }
            }

            // Right heavy
            if (balance < -1) {
                if (node.right.getBalance() <= 0) {
                    return leftRotate(node);
                } else {
                    return rightLeftRotate(node);
                }
            }

            return node;
        }

        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;
            return new AVLNode(x.data, x.left, new AVLNode(y.data, T2, y.right));
        }

        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;
            return new AVLNode(y.data, new AVLNode(x.data, x.left, T2), y.right);
        }

        private AVLNode leftRightRotate(AVLNode node) {
            return rightRotate(new AVLNode(node.data, leftRotate(node.left), node.right));
        }

        private AVLNode rightLeftRotate(AVLNode node) {
            return leftRotate(new AVLNode(node.data, node.left, rightRotate(node.right)));
        }

        public boolean search(int versionIndex, int value) {
            if (versionIndex < 0 || versionIndex >= versions.size()) {
                return false;
            }
            return searchNode(versions.get(versionIndex), value);
        }

        private boolean searchNode(AVLNode node, int value) {
            if (node == null) {
                return false;
            }
            if (value == node.data) {
                return true;
            }
            if (value < node.data) {
                return searchNode(node.left, value);
            }
            return searchNode(node.right, value);
        }

        public AVLNode getVersion(int index) {
            if (index < 0 || index >= versions.size()) {
                return null;
            }
            return versions.get(index);
        }

        public void printInOrder(AVLNode node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.data + " ");
                printInOrder(node.right);
            }
        }

        public void printVersion(int index) {
            System.out.print("Version " + index + ": ");
            AVLNode node = getVersion(index);
            printInOrder(node);
            System.out.println();
        }

        public int getNumberOfVersions() {
            return versions.size();
        }
    }

    public static void main(String[] args) {
        PersistentAVL tree = new PersistentAVL();

        System.out.println("=== Persistent AVL Tree Demo ===");

        int[] values = {10, 20, 5, 30};
        for (int value : values) {
            tree.insert(value);
        }

        // Print all versions
        for (int i = 0; i < tree.getNumberOfVersions(); i++) {
            tree.printVersion(i);
        }

        // Search in different versions
        System.out.println("\nSearch for 20 in version 1: " + tree.search(1, 20));
        System.out.println("Search for 30 in version 2: " + tree.search(2, 30));
        System.out.println("Search for 5 in version 0: " + tree.search(0, 5));
    }
}
