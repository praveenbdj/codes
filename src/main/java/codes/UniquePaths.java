package codes;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        System.out.println(
                up.uniquePaths(7, 3)
        );
        int size = 100_000;
        int[][] arr = new int[size][size];
    }

}
