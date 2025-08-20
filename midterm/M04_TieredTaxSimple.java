import java.io.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long totalTax = 0;

        /*
         * 時間複雜度: O(n)
         * 說明：
         * 1. 我們讀取 n 筆收入，並為每筆計算稅額。
         * 2. 每筆計算稅額的成本為 O(1)。
         * 3. 因此總體時間複雜度為 O(n)。
         */
        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine());
            long tax = calculateTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        /*
         * 時間複雜度: O(1)
         * 說明：
         * 1. 只需一次除法運算即可計算平均稅額。
         * 2. 無論 n 多大，此操作始終為常數時間。
         */
        long average = totalTax / n;
        System.out.println("Average: " + average);
    }

    /*
     * 時間複雜度: O(1)
     * 說明：
     * 1. 計算稅額僅需比較收入在哪個級距（最多 4 次判斷）。
     * 2. 每次計算只涉及固定的運算，成本為常數時間。
     */
    private static long calculateTax(long income) {
        if (income <= 120000) {
            return Math.round(income * 0.05);
        } else if (income <= 500000) {
            return Math.round(income * 0.12 - 6600);
        } else if (income <= 1000000) {
            return Math.round(income * 0.20 - 46600);
        } else {
            return Math.round(income * 0.30 - 146600);
        }
    }
}
