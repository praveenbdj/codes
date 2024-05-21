package codes;

//leetcode 122
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BestTimeForStock2 {

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int buy = prices[0];
        int sell = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < sell) {
                profit += (sell - buy);
                buy = prices[i];
                sell = prices[i];
            } else {
                sell = prices[i];
            }
        }

        return profit + (sell - buy);
    }

    public static void main(String[] args) {
        System.out.println(
                maxProfit(
                        new int[]{7, 1, 5, 3, 6, 4}
                )
        );

        System.out.println(
                maxProfit(
                        new int[]{1, 2, 3, 4, 5}
                )
        );

        System.out.println(
                maxProfit(
                        new int[]{7, 6, 4, 3, 1}
                )
        );
    }
}
