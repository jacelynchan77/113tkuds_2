
import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private Map<Integer, Integer> delayed;
    private int maxHeapSize, minHeapSize;
    private int k;

    public SlidingWindowMedian(int k) {
        this.k = k;
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        maxHeapSize = 0;
        minHeapSize = 0;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            maxHeapSize++;
        } else {
            minHeap.offer(num);
            minHeapSize++;
        }
        balanceHeaps();
    }

    private void remove(int num) {

        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            maxHeapSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            minHeapSize--;
            if (!minHeap.isEmpty() && num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

    private void balanceHeaps() {

        if (maxHeapSize > minHeapSize + 1) {
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
            prune(maxHeap);
        } else if (minHeapSize > maxHeapSize) {
            maxHeap.offer(minHeap.poll());
            minHeapSize--;
            maxHeapSize++;
            prune(minHeap);
        }
    }

    private double getMedian() {
        if (k % 2 == 1) {
            return (double) maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    public double[] medianSlidingWindow(int[] nums) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new double[0];
        }

        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
                remove(nums[i - k + 1]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian(3);
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("Input: [1,3,-1,-3,5,3,6,7], K=3");
        System.out.println("Output: " + Arrays.toString(swm.medianSlidingWindow(nums1)));
        // Expected: [1, -1, -1, 3, 5, 6]

        SlidingWindowMedian swm2 = new SlidingWindowMedian(2);
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Input: [1,2,3,4], K=2");
        System.out.println("Output: " + Arrays.toString(swm2.medianSlidingWindow(nums2)));
        // Expected: [1.5, 2.5, 3.5]
    }
}
