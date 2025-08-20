import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long gcd = gcd(a, b);
        long lcm = (a / gcd) * b; // divide first to avoid overflow

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }

    /*
     * 時間複雜度: O(log(min(a, b)))
     * 說明：
     * 1. 每次遞迴呼叫都會透過取模運算 (x % y) 使其中一個數字縮小。
     * 2. 歐幾里得演算法的步數與 log(min(a, b)) 成正比。
     * 3. 因此整體時間複雜度為 O(log(min(a, b)))。
     */
    private static long gcd(long x, long y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
