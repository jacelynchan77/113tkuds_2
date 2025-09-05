// file name: LC01_TwoSum_THSRHoliday.java

import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        // 使用 HashMap<需要的數, 索引>
        Map<Long, Integer> map = new HashMap<>();
        int idx1 = -1, idx2 = -1;

        for (int i = 0; i < n; i++) {
            long cur = seats[i];
            if (map.containsKey(cur)) {
                idx1 = map.get(cur);
                idx2 = i;
                break;
            }
            long need = target - cur;
            map.put(need, i);
        }

        if (idx1 == -1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(idx1 + " " + idx2);
        }
    }
}
