
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MinHeap {

    private final List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Get parent index
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Get left child index
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Get right child index
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Heapify up: maintain heap property after insertion
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    // Heapify down: maintain heap property after extraction
    private void heapifyDown(int i) {
        int minIdx = i;
        while (true) {
            int left = leftChild(minIdx);
            int right = rightChild(minIdx);
            int smallest = minIdx;

            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest == minIdx) {
                break;
            }
            Collections.swap(heap, minIdx, smallest);
            minIdx = smallest;
        }
    }

    // Insert value into heap
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Extract minimum value (root of heap)
    public int extractMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    // Peek minimum value without removing
    public int peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    // Check if heap is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        System.out.println("=== Min Heap Operation Demo ===");
        int[] values = {40, 30, 15, 20, 10};
        for (int value : values) {
            minHeap.insert(value);
            System.out.println("After inserting " + value + ": " + minHeap);
        }

        System.out.println("\nCurrent minimum value: " + minHeap.peek());

        System.out.println("\nExtracting min values one by one:");
        while (!minHeap.isEmpty()) {
            System.out.println("Extracted: " + minHeap.extractMin() + ", Remaining heap: " + minHeap);
        }
    }
}
