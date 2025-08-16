// File name: AVLTree.java

public class AVLTree {

    // Node class
    static class AVLNode {

        int data;
        AVLNode left, right;
        int height;

        AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }

        int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }

        void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }
    }

    private AVLNode root;

    // Right rotation
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // Left rotation
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

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
            return node; // No duplicates
        }
        node.updateHeight();
        int balance = node.getBalance();

        // Balance cases
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);        // Left Left

                }if (balance < -1 && data > node.right.data) {
            return leftRotate(node);       // Right Right

                }if (balance > 1 && data > node.left.data) {                                // Left Right
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {                               // Right Left
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Search node
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) {
            return false;
        }
        if (data == node.data) {
            return true;
        }
        return (data < node.data) ? searchNode(node.left, data) : searchNode(node.right, data);
    }

    // Find minimum node
    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        node.updateHeight();
        int balance = node.getBalance();

        // Balance cases
        if (balance > 1 && node.left.getBalance() >= 0) {
            return rightRotate(node);         // Left Left

                }if (balance > 1 && node.left.getBalance() < 0) {                                  // Left Right
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) {
            return leftRotate(node);        // Right Right

                }if (balance < -1 && node.right.getBalance() > 0) {                                 // Right Left
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Validate AVL tree
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Print tree in-order
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

    // Main method
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== AVL Tree Test ===");

        // Insert nodes
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("\nIn-order traversal (value(balance factor)):");
        tree.printTree();

        // Search test
        System.out.println("\nSearch 30: " + tree.search(30));
        System.out.println("Search 60: " + tree.search(60));

        // Delete test
        System.out.println("\nDeleting 30");
        tree.delete(30);
        System.out.println("Tree after deleting 30:");
        tree.printTree();

        // Validate AVL
        System.out.println("\nIs valid AVL tree: " + tree.isValidAVL());
    }
}
