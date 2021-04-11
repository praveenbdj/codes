package codes;

public class MaximalSquare {

    public static void main(String[] args) {
        MaximalSquare ms = new MaximalSquare();
        System.out.println(
                ms.maximalSquare(
                        new char[][]{{'1', '1'}, {'1', '1'}}
                )
        );
    }

    public int maximalSquare(char[][] matrix) {
        int sol = 0;
        char zero = 0, one = 1;
        for (int i = 0, matrixLength = matrix.length; i < matrixLength; i++) {
            if (matrix[i][0] == 49) {
                sol = 1;
                matrix[i][0] = one;
            } else
                matrix[i][0] = zero;
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 49) {
                sol = 1;
                matrix[0][i] = one;
            } else
                matrix[0][i] = zero;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 49) {
                    matrix[i][j] = (char) (min(matrix[i - 1][j], matrix[i][j - 1], matrix[i - 1][j - 1]) + one);
                    sol = Math.max(sol, matrix[i][j]);
                } else
                    matrix[i][j] = zero;
            }
        }
        return sol * sol;
    }

    public char min(char a, char b, char c) {
        return c < (a < b ? a : b) ? c : (a < b ? a : b);
    }

}
