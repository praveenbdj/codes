package codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//geeks for geeks
//https://practice.geeksforgeeks.org/problems/stock-buy-and-sell-1587115621/1#
public class StockBuyAndSell {

    ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        ArrayList<ArrayList<Integer>> sol = new ArrayList<>();
        boolean buy = false;
        for (int i = 0; i < n; i++) {
            if (!buy && i < n - 1 && A[i] < A[i + 1]) {
                sol.add(new ArrayList<>());
                sol.get(sol.size() - 1).add(i);
                buy = true;
            } else if (buy && ((i < n - 1 && A[i] > A[i + 1]) || i == n - 1)) {
                sol.get(sol.size() - 1).add(i);
                buy = false;
            }
        }
        return sol;
    }

    private int[] sampleGenerator(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt() & Integer.MAX_VALUE;
        }
        return arr;
    }

    public static void main(String[] args) {
        StockBuyAndSell buyAndSell = new StockBuyAndSell();
        long l = System.currentTimeMillis();
        int[] ints = buyAndSell.sampleGenerator(10_000_000);
        System.out.println(System.currentTimeMillis() - l);
        l = System.currentTimeMillis();
        Arrays.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
