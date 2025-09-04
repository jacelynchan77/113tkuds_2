// lt_31_NextPermutation.java

import java.util.*;

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}

// local testing class, must match file name
public class lt_31_NextPermutation {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3};
        sol.nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1, 3, 2]
    }
}

/*
解題邏輯與思路
1) 從右往左找到第一個下降點 i（nums[i] < nums[i+1]）。若不存在，表示目前已是最大排列，直接反轉整個陣列。
2) 在右側從右往左找到第一個大於 nums[i] 的元素 j，交換 nums[i] 與 nums[j]。
3) 將 [i+1, n-1] 反轉，使右半部分為最小遞增序列，得到下一個字典序排列。
時間複雜度：O(n)，空間複雜度：O(1)。
*/
