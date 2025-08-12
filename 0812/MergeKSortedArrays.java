
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Element implements Comparable<Element> {

    int value;
    int arrayIndex;
    int elementIndex;

    public Element(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }

    @Override
    public int compareTo(Element other) {
        return Integer.compare(this.value, other.value);
    }
}

public class MergeKSortedArrays {

    public static List<Integer> merge(List<int[]> arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                minHeap.offer(new Element(arrays.get(i)[0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element curr = minHeap.poll();
            result.add(curr.value);

            int nextIndex = curr.elementIndex + 1;
            if (nextIndex < arrays.get(curr.arrayIndex).length) {
                minHeap.offer(new Element(arrays.get(curr.arrayIndex)[nextIndex], curr.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test 1
        List<int[]> input1 = new ArrayList<>();
        input1.add(new int[]{1, 4, 5});
        input1.add(new int[]{1, 3, 4});
        input1.add(new int[]{2, 6});
        System.out.println("Input: [[1,4,5], [1,3,4], [2,6]]");
        System.out.println("Output: " + merge(input1));  // Expected: [1,1,2,3,4,4,5,6]

        // Test 2
        List<int[]> input2 = new ArrayList<>();
        input2.add(new int[]{1, 2, 3});
        input2.add(new int[]{4, 5, 6});
        input2.add(new int[]{7, 8, 9});
        System.out.println("Input: [[1,2,3], [4,5,6], [7,8,9]]");
        System.out.println("Output: " + merge(input2));  // Expected: [1,2,3,4,5,6,7,8,9]

        // Test 3
        List<int[]> input3 = new ArrayList<>();
        input3.add(new int[]{1});
        input3.add(new int[]{0});
        System.out.println("Input: [[1], [0]]");
        System.out.println("Output: " + merge(input3));  // Expected: [0,1]
    }
}
