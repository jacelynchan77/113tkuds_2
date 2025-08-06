
import java.util.*;

public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            System.out.println("Round " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
    }

    public static void bubbleSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("BubbleSort - Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Selection sort:");
        selectionSort(arr.clone());

        int[] arr2 = {64, 25, 12, 22, 11};
        bubbleSort(arr2);
    }
}
