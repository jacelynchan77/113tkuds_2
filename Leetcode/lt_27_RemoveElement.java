// file: lt_27_RemoveElement.java
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 指向「下一個非 val 元素」應填入的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}

public class lt_27_RemoveElement {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {3,2,2,3};
        int k1 = sol.removeElement(nums1, 3);
        System.out.print("k=" + k1 + ", nums=[");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + (i < nums1.length-1 ? "," : ""));
        }
        System.out.println("]"); // 期望：k=2, nums 前 2 個為 [2,2]

        int[] nums2 = {0,1,2,2,3,0,4,2};
        int k2 = sol.removeElement(nums2, 2);
        System.out.print("k=" + k2 + ", nums=[");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i] + (i < nums2.length-1 ? "," : ""));
        }
        System.out.println("]"); // 期望：k=5, nums 前 5 個為 [0,1,3,0,4] (順序可不同)
    }
}

/*
解題邏輯與思路：
1) 題目要求：移除陣列中所有等於 val 的元素，並返回剩下元素的數量 k；
   修改必須在原陣列中完成，不需要保留剩餘部分的值或順序。
2) 解法：使用雙指標（覆蓋法）。
   - 用 k 作為「下個非 val 元素要放的位置」。
   - 從頭到尾掃描 nums，若 nums[i] != val，就把 nums[i] 放到 nums[k]，並且 k++。
   - 若 nums[i] == val，就略過，表示丟棄該元素。
3) 掃描完畢後，k 即為非 val 元素的個數，陣列前 k 個元素即為結果。
   陣列後面的位置內容無所謂，因為題目允許忽略。
4) 複雜度分析：
   - 時間複雜度：O(n)，遍歷一次陣列。
   - 空間複雜度：O(1)，僅用到常數額外變數。
*/
