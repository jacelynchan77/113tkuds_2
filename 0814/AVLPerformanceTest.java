
import java.util.*;

public class AVLPerformanceTest {

    public static void main(String[] args) {
        System.out.println("=== AVL Tree Performance Test ===");

        AVLTree avlTree = new AVLTree();
        Random random = new Random();
        int n = 10000;

        // Insertion test
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            avlTree.insert(random.nextInt(100000));
        }
        long insertTime = System.nanoTime() - startTime;

        // Search test
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            avlTree.search(random.nextInt(100000));
        }
        long searchTime = System.nanoTime() - startTime;

        System.out.println("Time to insert " + n + " elements: "
                + insertTime / 1_000_000 + " ms");
        System.out.println("Time to search 1000 times: "
                + searchTime / 1_000_000 + " ms");
        System.out.println("Average insertion time complexity: O(log n)");
        System.out.println("Average search time complexity: O(log n)");
    }
}
