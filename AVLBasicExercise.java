// File: AVLBasicExercise.java

public class AVLBasicExercise {

    // Node class
    static class AVLNode {

        int data;
        AVLNode left, right;

        AVLNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private AVLNode root;

    // Insert node (standard BST insertion)
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
        }
        // duplicates are ignored
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

    // Calculate tree height
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Check if tree is a valid AVL tree
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

        int balance = leftHeight - rightHeight;
        if (balance < -1 || balance > 1) {
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
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    // Main method: test simplified AVL tree
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        System.out.println("=== Simplified AVL Tree Exercise ===");

        // Insert nodes
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int value : values) {
            System.out.println("Inserting: " + value);
            tree.insert(value);
        }

        // Print in-order
        System.out.print("In-order traversal: ");
        tree.printTree();

        // Search nodes
        System.out.println("Search 25: " + tree.search(25));
        System.out.println("Search 35: " + tree.search(35));

        // Height
        System.out.println("Tree height: " + tree.getHeight());

        // Validate AVL property
        System.out.println("Is valid AVL: " + tree.isValidAVL());
    }
}
