package codes;

//leetcode 121
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

/*
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * */
public class BestTimeForStock {

    /*
     * Logic: track the min element so far, for each new element, calculate profit, compare it with maxProfit so far.
     * If the new element is smaller than the min element, update the min element.
     *
     * */

    public static int maxProfit(int[] prices) {
        int profit = 0, min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                profit = Math.max(profit, price - min);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(
                maxProfit(new int[]{7,1,5,3,6,4})
        );
        System.out.println(
                maxProfit(new int[]{7,6,4,3,1})
        );
    }

}
