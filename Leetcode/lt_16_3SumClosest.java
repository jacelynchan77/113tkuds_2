// file: lt_16_3SumClosest.java
import java.io.*;
import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序
        int n = nums.length;
        int best = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int L = i + 1, R = n - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == target) return sum;
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum < target) L++;
                else R--;
            }
        }
        return best;
    }
}

public class lt_16_3SumClosest {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例 1
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println(sol.threeSumClosest(nums1, target1)); // 輸出 2

        // 測試案例 2
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println(sol.threeSumClosest(nums2, target2)); // 輸出 0
    }
}

/*
解題思路（中文）：
1. 對陣列進行排序，方便使用雙指標。
2. 外層固定一個索引 i，內層用左右指標 L=i+1、R=n-1。
3. 計算 sum = nums[i] + nums[L] + nums[R]：
   - 若等於 target，直接回傳。
   - 若更接近目標，更新 best。
   - 若 sum < target → L++；否則 R--。
4. 掃描結束後回傳 best。
時間複雜度 O(n^2)，空間 O(1)。
*/
