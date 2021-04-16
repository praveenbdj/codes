package codes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {

    private static final char DOT = '.';

    public void solveSudoku(char[][] board) {
        ArrayList<Set<Character>> rows = new ArrayList<>();
        ArrayList<Set<Character>> cols = new ArrayList<>();
        ArrayList<Set<Character>> mats = new ArrayList<>();
        populate(rows);
        populate(cols);
        populate(mats);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                add(rows, i, board[i][j]);
                add(cols, j, board[i][j]);
                addMats(mats, i, j, board[i][j]);
            }
        }

        search(board, rows, cols, mats, 0, 0);
    }

    private boolean search(char[][] board, ArrayList<Set<Character>> rows, ArrayList<Set<Character>> cols,
                           ArrayList<Set<Character>> mats, int i, int j) {
        if (i > 8) return true;
        if (board[i][j] == DOT) {
            for (char d = '1'; d <= '9'; d++) {
                if (
                        !contains(rows, i, d) && !contains(cols, j, d) && !containsMats(mats, i, j, d)
                ) {
                    add(board, rows, cols, mats, i, j, d);
                    if (search(board, rows, cols, mats, j == 8 ? i + 1 : i, j == 8 ? 0 : j + 1))
                        return true;
                    remove(board, rows, cols, mats, i, j, d);
                }
            }
            return false;
        } else
            return search(board, rows, cols, mats, j == 8 ? i + 1 : i, j == 8 ? 0 : j + 1);
    }

    public void populate(ArrayList<Set<Character>> rows) {
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
        }
    }

    public boolean contains(ArrayList<Set<Character>> array, int i, char ch) {
        return array.get(i).contains(ch);
    }

    public boolean containsMats(ArrayList<Set<Character>> array, int i, int j, char ch) {
        return array.get(matIndex(i, j)).contains(ch);
    }

    public void add(char[][] board, ArrayList<Set<Character>> rows, ArrayList<Set<Character>> cols,
                    ArrayList<Set<Character>> mats, int i, int j, char d) {
        board[i][j] = d;
        add(rows, i, d);
        add(cols, j, d);
        addMats(mats, i, j, d);
    }

    public void remove(char[][] board, ArrayList<Set<Character>> rows, ArrayList<Set<Character>> cols,
                       ArrayList<Set<Character>> mats, int i, int j, char d) {
        board[i][j] = DOT;
        rows.get(i).remove(d);
        cols.get(j).remove(d);
        mats.get(matIndex(i, j)).remove(d);
    }

    public void add(ArrayList<Set<Character>> array, int i, char ch) {
        if (ch != DOT)
            array.get(i).add(ch);
    }

    public void addMats(ArrayList<Set<Character>> array, int i, int j, char ch) {
        if (ch != DOT)
            array.get(matIndex(i, j)).add(ch);
    }

    private int matIndex(int i, int j) {
        return (i / 3) * 3 + j / 3;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku(board);
    }


}
