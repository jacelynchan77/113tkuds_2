// File: AVLDeleteExercise.java

public class AVLDeleteExercise {

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

    // AVLTree class
    static class AVLTree {

        private AVLNode root;

        // Insert (simple BST insert with balancing)
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
                return node; // duplicates ignored
            }
            node.updateHeight();
            return balanceNode(node, data);
        }

        // Delete node
        public void delete(int data) {
            root = deleteNode(root, data);
        }

        private AVLNode deleteNode(AVLNode node, int data) {
            if (node == null) {
                return null;
            }

            if (data < node.data) {
                node.left = deleteNode(node.left, data); 
            }else if (data > node.data) {
                node.right = deleteNode(node.right, data); 
            }else {
                // Node to delete found

                // Case 1: leaf or single child
                if (node.left == null || node.right == null) {
                    AVLNode temp = (node.left != null) ? node.left : node.right;
                    if (temp == null) { // leaf
                        node = null;
                    } else { // one child
                        node = temp;
                    }
                } else {
                    // Case 2: two children → find successor
                    AVLNode temp = findMin(node.right);
                    node.data = temp.data;
                    node.right = deleteNode(node.right, temp.data);
                }
            }

            if (node == null) {
                return null;
            }

            node.updateHeight();
            return rebalanceAfterDelete(node);
        }

        private AVLNode findMin(AVLNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // Balance node after insertion
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

        // Balance node after deletion
        private AVLNode rebalanceAfterDelete(AVLNode node) {
            int balance = node.getBalance();

            // Left Left
            if (balance > 1 && node.left.getBalance() >= 0) {
                return rightRotate(node);
            }
            // Left Right
            if (balance > 1 && node.left.getBalance() < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // Right Right
            if (balance < -1 && node.right.getBalance() <= 0) {
                return leftRotate(node);
            }
            // Right Left
            if (balance < -1 && node.right.getBalance() > 0) {
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
    }

    // Main method: test deletion
    public static void main(String[] args) {
        System.out.println("=== AVL Tree Deletion Exercise ===");

        AVLTree tree = new AVLTree();

        // Insert nodes
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            tree.insert(val);
        }

        System.out.print("Original tree: ");
        tree.printTree();

        // Delete leaf node
        System.out.println("Deleting leaf node 20...");
        tree.delete(20);
        tree.printTree();

        // Delete node with one child
        System.out.println("Deleting node with one child 30...");
        tree.delete(30);
        tree.printTree();

        // Delete node with two children
        System.out.println("Deleting node with two children 50...");
        tree.delete(50);
        tree.printTree();
    }
}
