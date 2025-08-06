
import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        if (a[0] < b[0]) {
            return concat(new int[]{a[0]}, mergeSortedArrays(Arrays.copyOfRange(a, 1, a.length), b));
        } else {
            return concat(new int[]{b[0]}, mergeSortedArrays(a, Arrays.copyOfRange(b, 1, b.length)));
        }
    }

    private static int[] concat(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static int kthSmallest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    public static boolean subsetSumExists(int[] arr, int target, int index) {
        if (target == 0) {
            return true;
        }
        if (index >= arr.length) {
            return false;
        }
        return subsetSumExists(arr, target - arr[index], index + 1)
                || subsetSumExists(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(arr));

        int[] a = {1, 3, 5}, b = {2, 4, 6};
        System.out.println("Merge sorted: " + Arrays.toString(mergeSortedArrays(a, b)));

        int[] arr2 = {7, 10, 4, 3, 20, 15};
        System.out.println("3rd smallest: " + kthSmallest(arr2, 3));

        int[] nums = {3, 34, 4, 12, 5, 2};
        System.out.println("Subset sum to 9 exists? " + subsetSumExists(nums, 9, 0));
    }
}
