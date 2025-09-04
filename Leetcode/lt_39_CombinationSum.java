import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            if (a[i] > remain) break;
            path.add(a[i]);
            dfs(a, i, remain - a[i], path, ans);
            path.remove(path.size() - 1);
        }
    }
}

public class lt_39_CombinationSum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] c1 = {2,3,6,7};
        System.out.println(sol.combinationSum(c1, 7)); // [[2,2,3],[7]]

        int[] c2 = {2,3,5};
        System.out.println(sol.combinationSum(c2, 8)); // [[2,2,2,2],[2,3,3],[3,5]]

        int[] c3 = {2};
        System.out.println(sol.combinationSum(c3, 1)); // []
    }
}

/*
解題邏輯與思路
1) 對 candidates 先排序，便於剪枝（當 a[i] > remain 即可停止）。
2) 回溯：在索引 i 處可選用 a[i]「不限次數」，因此遞迴時仍從 i 開始；若 remain 歸零則加入答案。
3) 以 path 暫存當前組合，探索後回溯彈出；用排序 + 剪枝避免無效分支，自然不會產生重複組合。
時間複雜度：最壞近似組合枚舉，但剪枝有效；空間複雜度：O(target/最小值) 的遞迴深度與暫存路徑。
*/
