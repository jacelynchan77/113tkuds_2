
public class AVLInsertExample {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("=== AVL Tree Insertion Demo ===");

        // Sequence of insertions that will cause imbalance
        int[] values = {10, 20, 30, 40, 50, 25};

        for (int value : values) {
            System.out.println("Inserting: " + value);
            tree.insert(value);
            System.out.print("Current tree state: ");
            tree.printTree();
            System.out.println();
        }

        System.out.println("=== Search Test ===");
        System.out.println("Search 25: " + tree.search(25));
        System.out.println("Search 35: " + tree.search(35));
    }
}
