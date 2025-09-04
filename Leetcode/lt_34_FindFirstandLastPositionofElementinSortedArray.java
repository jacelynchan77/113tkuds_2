import java.util.*;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int l = 0, r = nums.length - 1, bound = -1;
        while (l <= r) {
            int m = l + ((r - l) >>> 1);
            if (nums[m] == target) {
                bound = m;
                if (isFirst) r = m - 1;
                else l = m + 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return bound;
    }
}

public class lt_34_FindFirstandLastPositionofElementinSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(sol.searchRange(nums1, 8))); // [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(sol.searchRange(nums2, 6))); // [-1,-1]

        int[] nums3 = {};
        System.out.println(Arrays.toString(sol.searchRange(nums3, 0))); // [-1,-1]
    }
}

/*
解題邏輯與思路
題目要求 O(log n)，因此使用二分搜尋來找左右邊界：
1) findBound(nums, target, true)：尋找 target 的最左位置。若找到，繼續往左收縮區間。
2) findBound(nums, target, false)：尋找 target 的最右位置。若找到，繼續往右收縮區間。
3) 回傳 [first, last]，若找不到則為 [-1,-1]。
時間複雜度：O(log n)，空間複雜度：O(1)。
*/
