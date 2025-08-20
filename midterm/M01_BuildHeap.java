import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String type = br.readLine().trim(); // "max" or "min"
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * 時間複雜度: O(n)
         * 說明：
         * 1) Bottom-up 建堆從最後一個非葉節點開始往上做 heapify。
         * 2) 每個節點下沉的成本與其高度成正比，總和約為 2n。
         * 3) 因此總體複雜度為 O(n)，遠優於逐一插入的 O(n log n)。
         */
        buildHeap(arr, type.equals("max"));

        // Output result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }

    private static void buildHeap(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n, isMax);
        }
    }

    /*
     * 時間複雜度: O(log n)
     * 說明：
     * 1) 單一節點下沉最多經過樹高 h ≈ log n。
     * 2) 每層僅需常數次比較與交換。
     * 3) 因此單次 heapifyDown 為 O(log n)。
     */
    private static void heapifyDown(int[] arr, int i, int size, boolean isMax) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int chosen = i;

            if (left < size && compare(arr[left], arr[chosen], isMax)) {
                chosen = left;
            }
            if (right < size && compare(arr[right], arr[chosen], isMax)) {
                chosen = right;
            }

            if (chosen != i) {
                int temp = arr[i];
                arr[i] = arr[chosen];
                arr[chosen] = temp;
                i = chosen; // continue sinking
            } else {
                break;
            }
        }
    }

    // Compare function depending on heap type
    private static boolean compare(int a, int b, boolean isMax) {
        return isMax ? a > b : a < b;
    }
}

