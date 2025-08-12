
public class ValidMaxHeapChecker {

    public static boolean isMaxHeap(int[] arr) {
        int n = arr.length;

        // Last non-leaf node index
        int lastParentIndex = (n - 2) / 2;

        for (int i = 0; i <= lastParentIndex; i++) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            // Check left child
            if (leftChildIndex < n && arr[i] < arr[leftChildIndex]) {
                System.out.println("Violation at index " + leftChildIndex
                        + ": " + arr[leftChildIndex]
                        + " is greater than parent " + arr[i]);
                return false;
            }

            // Check right child
            if (rightChildIndex < n && arr[i] < arr[rightChildIndex]) {
                System.out.println("Violation at index " + rightChildIndex
                        + ": " + arr[rightChildIndex]
                        + " is greater than parent " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] testCase : testCases) {
            boolean result = isMaxHeap(testCase);
            System.out.print("Array: ");
            for (int num : testCase) {
                System.out.print(num + " ");
            }
            System.out.println("→ " + result);
            System.out.println("----");
        }
    }
}
