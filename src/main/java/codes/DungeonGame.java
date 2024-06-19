package codes;


import helper.Helper;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int ans = calculate(dungeon);
        return ans >= 0 ? 1 : (-1 * ans) + 1;
    }

    public int calculate(int[][] dungeon) {
        int rows = dungeon.length;
        if (rows == 0) return 0;
        int cols = dungeon[0].length;
        if (cols == 0) return 0;
        int[][] min = new int[rows][cols];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i == rows - 1 && j == cols - 1) {
                    min[i][j] = dungeon[i][j];
                } else {
                    min[i][j] = dungeon[i][j] +
                            Math.max(
                                    doesExist(dungeon, i + 1, j) ? min[i + 1][j] : Integer.MIN_VALUE,
                                    doesExist(dungeon, i, j + 1) ? min[i][j + 1] : Integer.MIN_VALUE
                            );
                }
                if (min[i][j] > 0) min[i][j] = 0;
            }
        }
        System.out.println(Helper.serialize(min));
        return min[0][0];
    }

    private boolean doesExist(int[][] dungeon, int i, int j) {
        return i >= 0 && i < dungeon.length && j >= 0 && j < dungeon[i].length;
    }

    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        System.out.println(
                game.calculate(
                        new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}
                )
        );
    }

}
