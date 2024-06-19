package codes;

import helper.Helper;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/n-queens/
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new LinkedList<>();
        boolean[][] mat = new boolean[n][n];
        trySeq(mat, 0, ans);
        return ans;
    }

    private void trySeq(boolean[][] mat, int i, List<List<String>> ans) {
        if (i == mat.length) {
            ans.add(takeSnapshot(mat));
            return;
        }
        for (int j = 0; j < mat.length; j++) {
            if (isValid(mat, i, j)) {
                mat[i][j] = true;
                trySeq(mat, i + 1, ans);
                mat[i][j] = false;
            }
        }
    }

    private List<String> takeSnapshot(boolean[][] mat) {
        List<String> list = new LinkedList<>();
        for (boolean[] row : mat) {
            StringBuilder s = new StringBuilder();
            for (boolean b : row) {
                s.append(b ? "Q" : ".");
            }
            list.add(s.toString());
        }
        return list;
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
                Helper.serialize(
                        new NQueens().solveNQueens(4)
                )
        );
    }

}
