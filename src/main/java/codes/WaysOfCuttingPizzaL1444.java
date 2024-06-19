package codes;


import helper.Helper;

public class WaysOfCuttingPizzaL1444 {

    public static void main(String[] args) {
        System.out.println(
                ways(new String[]{"A..", "A..", "..."}, 1)
        );
    }

    private static Integer[][][] dp;

    public static int ways(String[] pizza, int k) {
        int[][] apples = new int[pizza.length][pizza[0].length()];
        dp = new Integer[apples.length][apples[0].length][k + 1];

        apples[apples.length - 1][apples[0].length - 1] = pizza[apples.length - 1].charAt(apples[0].length - 1) == 'A' ? 1 : 0;

        for (int i = apples.length - 2; i >= 0; i--) {
            apples[i][apples[i].length - 1] = apples[i + 1][apples[i].length - 1] + (pizza[i].charAt(apples[i].length - 1) == 'A' ? 1 : 0);
        }

        for (int i = apples[0].length - 2; i >= 0; i--) {
            apples[apples.length - 1][i] = apples[apples.length - 1][i + 1] + (pizza[apples.length - 1].charAt(i) == 'A' ? 1 : 0);
        }

        for (int i = apples.length - 2; i >= 0; i--) {
            for (int j = apples[i].length - 2; j >= 0; j--) {
                apples[i][j] = apples[i + 1][j] + apples[i][j + 1] - apples[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }

        System.out.println(
                Helper.serialize(apples)
        );

        return
                ways(apples, k, 0, 0);
    }

    private static int ways(int[][] apples, int k, int i, int j) {
        if (dp[i][j][k] == null) {
            int r = apples[i][j];
            if (k == 1 && r > 0) return 1;
            int ways = 0;
            for (int i1 = i + 1; i1 < apples.length; i1++) {
                if (apples[i1][j] < r) {
                    ways = updateWays(ways, ways(apples, k - 1, i1, j));
                }
            }
            for (int j1 = j + 1; j1 < apples[0].length; j1++) {
                if (apples[i][j1] < r) {
                    ways = updateWays(ways, ways(apples, k - 1, i, j1));
                }
            }
            dp[i][j][k] = ways;
        }
        return dp[i][j][k];
    }

    private static int updateWays(int ways, int add) {
        if (add > 0) {
            return (ways + add) % 1_000_000_007;
        }
        return ways;
    }

}
