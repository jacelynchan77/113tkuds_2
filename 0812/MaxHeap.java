
import java.util.*;

public class MaxHeap {

    // Use ArrayList as the underlying storage structure
    // ArrayList is a dynamic array that can resize automatically
    private List<Integer> heap;

    // Constructor: initialize an empty heap
    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Helper method to get the parent index
    // Input: child index i
    // Output: parent index
    // Formula: (i - 1) / 2 (integer division truncates decimals)
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Get left child index
    // Formula: 2 * i + 1
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Get right child index
    // Formula: 2 * i + 2
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Heapify Up: maintain heap property after insertion
    // When a new element is inserted, it may break the max heap property
    // Compare it with its parent and swap if necessary
    private void heapifyUp(int i) {
        // Loop conditions:
        // 1. i > 0: ensure not the root node (root has no parent)
        // 2. heap.get(i) > heap.get(parent(i)): child is greater than parent
        while (i > 0 && heap.get(i) > heap.get(parent(i))) {
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    // Heapify Down: maintain heap property after removal
    // After removing the root, we need to restore max heap property
    private void heapifyDown(int i) {
        int maxIdx = i;
        int left = leftChild(i);
        int right = rightChild(i);

        // Check if left child exists and is greater than current max
        if (left < heap.size() && heap.get(left) > heap.get(maxIdx)) {
            maxIdx = left;
        }

        // Check if right child exists and is greater than current max
        if (right < heap.size() && heap.get(right) > heap.get(maxIdx)) {
            maxIdx = right;
        }

        // If maxIdx changed, swap and continue heapifying down
        if (maxIdx != i) {
            Collections.swap(heap, i, maxIdx);
            heapifyDown(maxIdx);
        }
    }

    // Public method to insert a new value
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Extract the maximum value (root)
    public int extractMax() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        int max = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heapifyDown(0);
        }

        return max;
    }

    // Peek at the maximum value without removing
    public int peek() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.get(0);
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Get the size of the heap
    public int size() {
        return heap.size();
    }

    // Display the heap contents (for debugging)
    public void display() {
        System.out.println("Heap contents: " + heap);
    }

    // Main method: demonstrate Max Heap usage
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        System.out.println("=== Max Heap Operation Demo ===");

        int[] values = {10, 20, 15, 30, 40};

        for (int value : values) {
            maxHeap.insert(value);
            System.out.println("After inserting " + value + ": " + maxHeap.heap);
        }

        System.out.println("\nCurrent max value: " + maxHeap.peek());

        System.out.println("\nExtracting max values in order:");
        while (!maxHeap.isEmpty()) {
            int max = maxHeap.extractMax();
            System.out.println("Extracted: " + max + ", Remaining heap: " + maxHeap.heap);
        }
    }
}
