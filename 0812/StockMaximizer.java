
import java.util.PriorityQueue;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K == 0) {
            return 0;
        }

        int n = prices.length;

        // If K >= n/2, can do unlimited transactions
        if (K >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // Find all ascending intervals (profits)
        PriorityQueue<Integer> profits = new PriorityQueue<>((a, b) -> b - a); // max heap for profits

        int i = 0;
        while (i < n - 1) {
            // Find valley
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = prices[i];

            // Find peak
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = prices[i];

            if (sell > buy) {
                profits.offer(sell - buy);
            }
            i++;
        }

        int maxProfit = 0;
        for (int j = 0; j < K && !profits.isEmpty(); j++) {
            maxProfit += profits.poll();
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        int K1 = 2;
        System.out.println(maxProfit(prices1, K1));  // 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int K2 = 2;
        System.out.println(maxProfit(prices2, K2));  // 7

        int[] prices3 = {1, 2, 3, 4, 5};
        int K3 = 2;
        System.out.println(maxProfit(prices3, K3));  // 4
    }
}
