
import java.util.*;

public class HeapBuilder {

    private List<Integer> heap;

    public HeapBuilder(int[] arr) {
        heap = new ArrayList<>();
        // Copy array elements to heap
        for (int value : arr) {
            heap.add(value);
        }
        buildHeap();
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void heapifyDown(int i) {
        int maxIdx = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left) > heap.get(maxIdx)) {
            maxIdx = left;
        }

        if (right < heap.size() && heap.get(right) > heap.get(maxIdx)) {
            maxIdx = right;
        }

        if (maxIdx != i) {
            Collections.swap(heap, i, maxIdx);
            heapifyDown(maxIdx);
        }
    }

    // Bottom-up construction: start from the last non-leaf node and heapify down
    private void buildHeap() {
        // Index of the last non-leaf node
        int startIdx = (heap.size() / 2) - 1;

        System.out.println("Starting heap construction, adjusting from index " + startIdx);
        System.out.println("Initial array: " + heap);

        for (int i = startIdx; i >= 0; i--) {
            System.out.println("\nAdjusting index " + i + " (value: " + heap.get(i) + ")");
            heapifyDown(i);
            System.out.println("After adjustment: " + heap);
        }
    }

    // Validate whether this is a valid Max Heap
    public boolean isValidHeap() {
        for (int i = 0; i < heap.size(); i++) {
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < heap.size() && heap.get(i) < heap.get(left)) {
                return false;
            }
            if (right < heap.size() && heap.get(i) < heap.get(right)) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        System.out.println("Final Heap: " + heap);
        System.out.println("Is valid Max Heap: " + isValidHeap());
    }

    public static void main(String[] args) {
        System.out.println("=== Demo: Build Heap from Array ===");

        int[] arr = {4, 10, 3, 5, 1, 15, 20, 17};
        System.out.println("Original array: " + Arrays.toString(arr));

        HeapBuilder heapBuilder = new HeapBuilder(arr);
        heapBuilder.display();

        // Display tree structure
        System.out.println("\nTree structure visualization:");
        System.out.println("      " + heapBuilder.heap.get(0));
        System.out.println("    /   \\");
        System.out.println("   " + heapBuilder.heap.get(1) + "     " + heapBuilder.heap.get(2));
        System.out.println("  / \\   / \\");
        System.out.println(" " + heapBuilder.heap.get(3) + "   " + heapBuilder.heap.get(4) + " " + heapBuilder.heap.get(5) + "   " + heapBuilder.heap.get(6));
        if (heapBuilder.heap.size() > 7) {
            System.out.println("/");
            System.out.println(heapBuilder.heap.get(7));
        }
    }
}
