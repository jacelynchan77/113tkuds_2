// lt_01_TwoSum.java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        // According to constraints, exactly one solution exists
        throw new IllegalArgumentException("No solution found");
    }
}

public class lt_01_TwoSum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        int[] nums1 = {2, 7, 11, 15};
        int[] res1 = sol.twoSum(nums1, 9);
        System.out.println(Arrays.toString(res1)); // [0, 1]

        // Example 2
        int[] nums2 = {3, 2, 4};
        int[] res2 = sol.twoSum(nums2, 6);
        System.out.println(Arrays.toString(res2)); // [1, 2]

        // Example 3
        int[] nums3 = {3, 3};
        int[] res3 = sol.twoSum(nums3, 6);
        System.out.println(Arrays.toString(res3)); // [0, 1]
    }
}
