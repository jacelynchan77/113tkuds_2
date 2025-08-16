// AVLRotations.java

public class AVLRotations {

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

    // Insert a value
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

    // Search
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

    // Delete
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

        if (balance > 1 && node.left.getBalance() >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // In-order traversal
    public void inorder() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(AVLNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            inorderTraversal(node.right);
        }
    }

    // Pre-order traversal
    public void preorder() {
        preorderTraversal(root);
        System.out.println();
    }

    private void preorderTraversal(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Main method
    public static void main(String[] args) {
        AVLRotations tree = new AVLRotations();

        System.out.println("=== AVL Tree Test ===");

        int[] values = {10, 20, 30, 40, 50, 25};
        System.out.println("Inserting nodes: 10, 20, 30, 40, 50, 25");
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("\nIn-order traversal (value(balance)):");
        tree.inorder();

        System.out.println("Pre-order traversal (value(balance)):");
        tree.preorder();

        System.out.println("\nSearching for 30: " + tree.search(30));
        System.out.println("Searching for 60: " + tree.search(60));

        System.out.println("\nDeleting 30");
        tree.delete(30);
        System.out.println("In-order after deletion:");
        tree.inorder();
        System.out.println("Pre-order after deletion:");
        tree.preorder();
    }
}
