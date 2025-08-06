
import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i < a.length) {
            merged[k++] = a[i++];
        }
        while (j < b.length) {
            merged[k++] = b[j++];
        }
        return merged;
    }

    public static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] first = Arrays.copyOfRange(arr, 0, mid);
        int[] second = Arrays.copyOfRange(arr, mid, arr.length);
        System.out.println("First half: " + Arrays.toString(first));
        System.out.println("Second half: " + Arrays.toString(second));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5, 6, 6, 7};
        System.out.println("Original: " + Arrays.toString(arr));
        System.out.println("Remove duplicates: " + Arrays.toString(removeDuplicates(arr)));

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        System.out.println("Merge sorted: " + Arrays.toString(mergeSortedArrays(a, b)));

        System.out.println("Most frequent: " + mostFrequentElement(arr));
        splitArray(arr);
    }
}
