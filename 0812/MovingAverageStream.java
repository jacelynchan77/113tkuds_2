
import java.util.*;

public class MovingAverageStream {

    private int size;
    private Queue<Integer> window;
    private long sum;

    private PriorityQueue<Integer> maxHeap; // smaller half
    private PriorityQueue<Integer> minHeap; // larger half

    private Map<Integer, Integer> delayed; // lazy removal for median heaps

    private TreeMap<Integer, Integer> countMap; // for min & max tracking

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();

        this.countMap = new TreeMap<>();
    }

    public double next(int val) {
        if (window.size() == size) {
            int removed = window.poll();
            sum -= removed;

            // Remove from countMap
            countMap.put(removed, countMap.get(removed) - 1);
            if (countMap.get(removed) == 0) {
                countMap.remove(removed);
            }

            // Mark delayed for median heaps
            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);

            // Remove from heaps lazily
            if (!maxHeap.isEmpty() && removed <= maxHeap.peek()) {
                pruneHeap(maxHeap);
            } else {
                pruneHeap(minHeap);
            }
        }

        window.offer(val);
        sum += val;

        // Add new val to heaps for median
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }

        // Update countMap
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        // Balance heaps
        balanceHeaps();

        return (double) sum / window.size();
    }

    public double getMedian() {
        if (window.isEmpty()) {
            return 0;
        }

        if (window.size() % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    public int getMin() {
        if (window.isEmpty()) {
            throw new NoSuchElementException("Window is empty");
        }
        return countMap.firstKey();
    }

    public int getMax() {
        if (window.isEmpty()) {
            throw new NoSuchElementException("Window is empty");
        }
        return countMap.lastKey();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            pruneHeap(maxHeap);
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            pruneHeap(minHeap);
        }
        pruneHeap(maxHeap);
        pruneHeap(minHeap);
    }

    private void pruneHeap(PriorityQueue<Integer> heap) {
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

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.67
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
