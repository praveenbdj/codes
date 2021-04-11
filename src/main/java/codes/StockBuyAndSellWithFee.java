package codes;

//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3674/
public class StockBuyAndSellWithFee {

    public static void main(String[] args) {
        System.out.println(
                maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)
        );
    }

    public static int maxProfit(int[] prices, int fee) {
        int profit = 0, min = 0, max = 0;
        int n = prices.length;
        boolean bought = false;
        for (int i = 0; i < n; i++) {
            if (!bought && i < n - 1 && prices[i] < prices[i + 1]) {
                min = max = prices[i];
                bought = true;
            } else if (prices[i] < min) {
                min = max = prices[i];
            } else if (prices[i] > max) {
                max = prices[i];
            }
            if (bought && max - fee > min && (i == n - 1 || prices[i + 1] + fee < max)) {
                bought = false;
                profit += (max - min) - fee;
            }
        }
        return profit;
    }


}
