package codes;

//https://leetcode.com/problems/unique-paths-iii/
public class UniquePathsIII {
    public int uniquePathsIII(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int si = 0, sj = 0, total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    total++;
                } else if (grid[i][j] == 1) {
                    si = i;
                    sj = j;
                }
            }
        }
        return uniquePaths(grid, visited, si, sj, total + 1);
    }

    private int uniquePaths(int[][] grid, boolean[][] visited, int i, int j, int total) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        if (grid[i][j] == 2) {
            if (total == 0) {
                return 1;
            }
            return 0;
        }
        if (grid[i][j] == -1) {
            return 0;
        }
        visited[i][j] = true;
        total--;
        int paths = uniquePaths(grid, visited, i + 1, j, total) +
                uniquePaths(grid, visited, i - 1, j, total) +
                uniquePaths(grid, visited, i, j + 1, total) +
                uniquePaths(grid, visited, i, j - 1, total);
        visited[i][j] = false;
        return paths;
    }

    public static void main(String[] args) {
        System.out.println(
                new UniquePathsIII().uniquePathsIII(
                        new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}
                )
        );
    }
}
