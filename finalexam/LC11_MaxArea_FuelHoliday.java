// file name: LC11_MaxArea_FuelHoliday.java

import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        System.out.println(maxArea(heights));
    }

    public static long maxArea(int[] h) {
        int left = 0, right = h.length - 1;
        long max = 0;

        while (left < right) {
            int width = right - left;
            long area = (long) width * Math.min(h[left], h[right]);
            max = Math.max(max, area);

            // 移動較短邊
            if (h[left] < h[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
