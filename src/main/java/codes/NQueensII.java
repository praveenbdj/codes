package codes;

//https://leetcode.com/problems/n-queens-ii/
public class NQueensII {

    public int totalNQueens(int n) {
        boolean[][] mat = new boolean[n][n];
        return trySeq(mat, 0);
    }

    private int trySeq(boolean[][] mat, int i) {
        if (i == mat.length) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < mat.length; j++) {
            if (isValid(mat, i, j)) {
                mat[i][j] = true;
                ans += trySeq(mat, i + 1);
                mat[i][j] = false;
            }
        }
        return ans;
    }

    private boolean isValid(boolean[][] mat, int i, int j) {
        for (int i1 = 0; i1 < mat.length; i1++) {
            if (mat[i][i1] || mat[i1][j]) return false;
        }
        for (int a = 1; a < mat.length; a++) {
            if (
                    checkPresence(mat, i + a, j + a) ||
                            checkPresence(mat, i + a, j - a) ||
                            checkPresence(mat, i - a, j + a) ||
                            checkPresence(mat, i - a, j - a)
            ) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPresence(boolean[][] mat, int i, int j) {
        if (exists(mat, i, j)) {
            return mat[i][j];
        }
        return false;
    }

    private boolean exists(boolean[][] mat, int i, int j) {
        return i >= 0 && j >= 0 && i < mat.length && j < mat.length;
    }

    public static void main(String[] args) {
        System.out.println(
                new NQueensII().totalNQueens(9)
        );
    }

}
