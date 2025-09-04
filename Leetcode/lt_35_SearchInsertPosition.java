
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >>> 1);
            if (nums[m] == target) return m;
            else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l; // 插入位置
    }
}

public class lt_35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1,3,5,6};
        System.out.println(sol.searchInsert(nums1, 5)); // 2

        int[] nums2 = {1,3,5,6};
        System.out.println(sol.searchInsert(nums2, 2)); // 1

        int[] nums3 = {1,3,5,6};
        System.out.println(sol.searchInsert(nums3, 7)); // 4
    }
}

/*
解題邏輯與思路
使用二分搜尋：
1) 初始化 l=0, r=n-1。
2) 取中點 m，若 nums[m]==target 則直接回傳 m。
3) 若 nums[m]<target，縮小到右半區間；否則縮小到左半區間。
4) 當搜尋結束時，l 即為 target 應插入的位置。
時間複雜度：O(log n)，空間複雜度：O(1)。
*/
