import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), ans);
        return ans;
        }

    private void dfs(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> ans) {
        if (remain == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            if (i > start && a[i] == a[i - 1]) continue; // 跳過同層重複
            if (a[i] > remain) break;
            path.add(a[i]);
            dfs(a, i + 1, remain - a[i], path, ans);      // 每數字只能用一次
            path.remove(path.size() - 1);
        }
    }
}

public class lt_40_CombinationSumII {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] c1 = {10,1,2,7,6,1,5};
        System.out.println(sol.combinationSum2(c1, 8)); // [[1,1,6],[1,2,5],[1,7],[2,6]]

        int[] c2 = {2,5,2,1,2};
        System.out.println(sol.combinationSum2(c2, 5)); // [[1,2,2],[5]]
    }
}

/*
解題邏輯與思路
1) 先排序，便於剪枝與去重。
2) 回溯枚舉：對索引 start 之後的元素進行迭代；若 a[i] == a[i-1] 且 i>start，跳過以避免同層重複。
3) 每個元素最多用一次，因此遞迴到下一層時使用 i+1 作為新的起點；remain 歸零則加入答案。
4) 若 a[i] > remain 直接中止該層（剪枝）。
時間複雜度：受輸出大小影響（最壞接近組合枚舉），空間複雜度：O(n) 遞迴深度。
*/
