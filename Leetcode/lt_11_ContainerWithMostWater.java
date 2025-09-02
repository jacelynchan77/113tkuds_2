import java.io.*;


class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int best = 0;
        // 使用雙指針：一個從左邊開始，一個從右邊開始
        while (i < j) {
            // 以較短的一邊為高，寬度為 j - i
            int h = Math.min(height[i], height[j]);
            best = Math.max(best, (j - i) * h);

            // 移動較短的邊，才有機會得到更大的高度
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return best;
    }
}

// 本地測試主程式（LeetCode 不需要這段）
public class lt_11_ContainerWithMostWater {
    private static int[] readArray(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) break;
        }
        if (line == null) return new int[0];

        // 移除中括號
        line = line.replaceAll("^\\[|\\]$", "").trim();
        if (line.isEmpty()) return new int[0];

        // 以逗號或空白分割
        String[] parts = line.split("[,\\s]+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter height array (e.g. [1,8,6,2,5,4,8,3,7]): ");
        int[] height = readArray(br);

        Solution sol = new Solution();
        int ans = sol.maxArea(height);
        System.out.println("Max area = " + ans);
    }
}

/*
解題思路：
1. 題目要求：找出兩條線所形成的容器，使得盛水量最大。
2. 如果暴力解法：枚舉所有兩條線組合，計算面積，時間複雜度 O(n^2)，會超時。
3. 最佳解法：使用「雙指針」：
   - 一個指針在陣列左端 (i=0)，另一個在右端 (j=n-1)。
   - 每次計算 (j - i) * min(height[i], height[j])。
   - 為了嘗試找到更大高度，移動「較短」的那一邊：
       - 若 height[i] < height[j]，則 i++。
       - 否則 j--。
   - 因為移動較長的一邊只會讓寬度縮小，且高度無法提升。
4. 如此只需 O(n) 就能求解，空間複雜度 O(1)。
*/
