import java.io.*;
import java.util.*;

// 題目：3Sum
// 給定整數陣列 nums，找出所有不重複的三元組 [a,b,c] 使得 a+b+c = 0。
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;

        Arrays.sort(nums); // 先排序，便於雙指針與去重
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 去重 1：固定點 i 與上一個值相同則略過（避免同樣的起點重複）
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 最小值都 > 0 時，可提前結束（因為排序後後面更大）
            if (nums[i] > 0) break;

            int L = i + 1, R = n - 1;
            while (L < R) {
                long sum = (long) nums[i] + nums[L] + nums[R]; // 用 long 避免極端相加溢位
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    L++;
                    R--;
                    // 去重 2：移動左指針，跳過相同值
                    while (L < R && nums[L] == nums[L - 1]) L++;
                    // 去重 3：移動右指針，跳過相同值
                    while (L < R && nums[R] == nums[R + 1]) R--;
                } else if (sum < 0) {
                    L++; // 總和太小，左指針右移增大總和
                } else {
                    R--; // 總和太大，右指針左移減小總和
                }
            }
        }
        return ans;
    }
}

// 本地測試主程式（LeetCode 不需要）
public class lt_15_3Sum {
    private static int[] readArray(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) break;
        }
        if (line == null) return new int[0];

        // 移除 [ ]（若存在）
        line = line.replaceAll("^\\[|\\]$", "").trim();
        if (line.isEmpty()) return new int[0];

        String[] parts = line.split("[,\\s]+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    // 輸出為類 JSON 格式，方便核對
    private static String toJsonTriplets(List<List<Integer>> triples) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < triples.size(); i++) {
            List<Integer> t = triples.get(i);
            sb.append("[");
            for (int j = 0; j < t.size(); j++) {
                sb.append(t.get(j));
                if (j + 1 < t.size()) sb.append(",");
            }
            sb.append("]");
            if (i + 1 < triples.size()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter nums array (e.g. [-1,0,1,2,-1,-4]): ");
        int[] nums = readArray(br);

        Solution sol = new Solution();
        List<List<Integer>> res = sol.threeSum(nums);
        System.out.println(toJsonTriplets(res));
    }
}

/*
解題思路：
1) 排序 + 雙指針（經典做法）
   - 先將陣列排序（O(n log n)）。
   - 以 i 作為固定點（從 0 到 n-3），對每個 i 之後的子區間用雙指針 L, R 尋找兩數和 = -nums[i]。
   - 因為排序後，若總和過小就 L++，過大就 R--，能在 O(n) 內處理一個固定點。

2) 去重機制（關鍵避免重複三元組）：
   - 固定點去重：若 nums[i] 與 nums[i-1] 相同，略過該 i。
   - 內層配對去重：每次找到一組解後，同值的 L 與 R 連續跳過，避免重複結果。

3) 為何可提前結束：
   - 若 nums[i] > 0，因排序使得後續數皆 ≥ nums[i]，三數和不可能為 0，直接 break。

4) 複雜度：
   - 時間：排序 O(n log n) + 外圈 O(n) × 內圈 O(n) ≈ O(n^2)。
   - 空間：O(1) 額外空間（不計輸出）。

5) 範例：
   - nums = [-1,0,1,2,-1,-4] → 排序後 [-4,-1,-1,0,1,2]
   - i = 0 → -4，(L,R) 尋找 4 → 無
   - i = 1 → -1，(L,R) 尋找 1 → 得到 [-1, -1, 2] 與 [-1, 0, 1]
   - 去重後輸出 [[-1,-1,2],[-1,0,1]]。
*/
