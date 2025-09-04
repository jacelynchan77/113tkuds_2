
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >>> 1);
            if (nums[m] == target) return m;
            if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target < nums[m]) r = m - 1;
                else l = m + 1;
            } else {
                if (nums[m] < target && target <= nums[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }
}

public class lt_33_SearchinRotatedSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println(sol.search(nums1, 0)); // expect 4

        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println(sol.search(nums2, 3)); // expect -1

        int[] nums3 = {1};
        System.out.println(sol.search(nums3, 0)); // expect -1
    }
}

/*
解題邏輯與思路
使用改良二分搜尋：
1) 每次計算中點 m，若 nums[m] == target，回傳 m。
2) 若左半區間 [l..m] 有序 (nums[l] <= nums[m])：
   - 若 target 落在 [nums[l], nums[m])，縮小搜尋範圍到左半。
   - 否則搜尋右半。
3) 否則右半區間有序：
   - 若 target 落在 (nums[m], nums[r]]，縮小搜尋範圍到右半。
   - 否則搜尋左半。
時間複雜度 O(log n)，空間複雜度 O(1)。
*/
