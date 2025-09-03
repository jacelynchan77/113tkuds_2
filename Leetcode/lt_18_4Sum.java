// file: lt_18_4Sum.java
import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            long min1 = (long)nums[i] + nums[i+1] + nums[i+2] + nums[i+3];
            if (min1 > target) break;
            long max1 = (long)nums[i] + nums[n-1] + nums[n-2] + nums[n-3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                long min2 = (long)nums[i] + nums[j] + nums[j+1] + nums[j+2];
                if (min2 > target) break;
                long max2 = (long)nums[i] + nums[j] + nums[n-1] + nums[n-2];
                if (max2 < target) continue;

                int L = j + 1, R = n - 1;
                while (L < R) {
                    long sum = (long)nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        int lv = nums[L], rv = nums[R];
                        while (L < R && nums[L] == lv) L++;
                        while (L < R && nums[R] == rv) R--;
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return res;
    }
}

public class lt_18_4Sum {
    public static void main(String[] args) {
        // 簡單自測
        Solution sol = new Solution();
        System.out.println(sol.fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println(sol.fourSum(new int[]{2,2,2,2,2}, 8));
    }
}

/*
解題邏輯與思路：
1) 先將陣列排序，方便去重與使用雙指標。
2) 外層用兩層索引 i、j 固定前兩個數；內層對剩餘區間以 L、R（左右指標）夾擊尋找另外兩數。
3) 針對 i、j、L、R 計算四數和 sum：
   - 若 sum == target，收集一組解，並跳過相同值以避免重複解（對 L、R 均去重）。
   - 若 sum < target，L++ 以增大總和；若 sum > target，R-- 以減小總和。
4) 去重策略：
   - 外層 i 與 j 都需跳過與前一個相同的數值，避免產生相同起點的組合。
   - 內層命中後，L 與 R 也要跳過重複值。
5) 剪枝優化（以 long 計算避免整數溢位）：
   - 對每個 i，計算「最小可能和」與「最大可能和」，若已超過/不足 target，提前 break/continue。
   - 對每個 j 同理處理。
時間複雜度：O(n^3)，空間複雜度：O(1)（結果列表除外）。
*/
