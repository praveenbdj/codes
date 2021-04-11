package codes;

public class SuperEggDropL887 {

    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        return find(k, n, dp);
    }

    private int find(int k, int n, int[][] dp) {
        if (k == 1 || n <= 1) return n;
        if (dp[k][n] == 0) {
            int moves = Integer.MAX_VALUE;
            int l = 1, r = n;
            while (l <= r) {
                int m = (l + r) / 2;
                int left = find(k - 1, m - 1, dp);
                int right = find(k, n - m, dp);
                moves = Math.min(
                        Math.max(left, right),
                        moves
                );
                if (left == right || l == r) break;
                if (left < right) {
                    l = m + 1;
                } else r = m;
            }
            dp[k][n] = moves + 1;
        }
        return dp[k][n];
    }

    public static void main(String[] args) {
        SuperEggDropL887 eggDrop = new SuperEggDropL887();
        System.out.println(
                eggDrop.superEggDrop(2, 9)
        );
    }

}
