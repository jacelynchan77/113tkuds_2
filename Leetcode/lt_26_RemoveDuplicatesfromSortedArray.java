// file: lt_26_RemoveDuplicatesfromSortedArray.java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int k = 1; // 指向下一個應放置「唯一元素」的位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}

public class lt_26_RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {1,1,2};
        int k1 = sol.removeDuplicates(nums1);
        System.out.print("k=" + k1 + ", nums=[");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + (i < nums1.length-1 ? "," : ""));
        }
        System.out.println("]"); // 期望：k=2, nums=[1,2,2]

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int k2 = sol.removeDuplicates(nums2);
        System.out.print("k=" + k2 + ", nums=[");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i] + (i < nums2.length-1 ? "," : ""));
        }
        System.out.println("]"); // 期望：k=5, nums=[0,1,2,3,4,2,2,3,3,4]
    }
}

/*
解題邏輯與思路：
1) 題目要求「移除排序陣列中的重複元素，保留每個唯一元素一次」，
   並且必須在原地修改陣列，不使用額外空間。
2) 由於陣列已經是非遞減排序，可以使用「雙指標」技巧：
   - 用 k 指標標記「下個應填入唯一元素的位置」，初始為 1。
   - 從 i=1 開始遍歷，若 nums[i] != nums[k-1]，表示找到新的唯一元素，
     則將 nums[i] 複製到 nums[k]，並將 k++。
3) 遍歷完成後，k 即為唯一元素的數量，陣列前 k 個元素即為結果。
4) 後面的位置內容不重要，因為題目允許保留任意值。
時間複雜度：O(n)，遍歷一次陣列。
空間複雜度：O(1)，僅使用常數變數。
*/
