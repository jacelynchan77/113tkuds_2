
import java.util.PriorityQueue;

public class KthSmallestElement {

    // Method 1: Max Heap of size K
    public static int findKthSmallest_MaxHeap(int[] nums, int k) {
        // Max heap in Java: PriorityQueue with reversed comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove largest in heap to keep only k smallest
            }
        }
        // Root of maxHeap is kth smallest element
        return maxHeap.peek();
    }

    // Method 2: Min Heap and extract min K times
    public static int findKthSmallest_MinHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];

            System.out.println("Array: ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println("\nK = " + k);

            int resMaxHeap = findKthSmallest_MaxHeap(arr, k);
            int resMinHeap = findKthSmallest_MinHeap(arr, k);

            System.out.println("Method 1 (Max Heap size K) result: " + resMaxHeap);
            System.out.println("Method 2 (Min Heap extract K times) result: " + resMinHeap);
            System.out.println("----");
        }
    }
}
