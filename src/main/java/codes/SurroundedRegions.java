package codes;

//leetcode 130
public class SurroundedRegions {

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char T = 'T';

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0, false);
            dfs(board, i, board[i].length - 1, false);
        }
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i, false);
            dfs(board, board.length - 1, i, false);
        }
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == O) board[i][j] = X;
                if (board[i][j] == T) board[i][j] = O;
            }
        }

    }

    private void dfs(char[][] board, int i, int j, boolean p) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length)
            return;
        if (board[i][j] == X || board[i][j] == T)
            return;
        if (!(i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1))
            board[i][j] = T;
        else if (p) return;
        dfs(board, i + 1, j, true);
        dfs(board, i - 1, j, true);
        dfs(board, i, j + 1, true);
        dfs(board, i, j - 1, true);
    }


}
