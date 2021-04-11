package codes;

public class MatrixPrefixSum {

    private int[][] matrix;

    public static void main(String[] args) {
        MatrixPrefixSum sum = new MatrixPrefixSum(
                new int[][]{{1, 2}}
        );
        System.out.println(
                sum.sumRegion(0, 0, 0, 1)
        );
    }

    public MatrixPrefixSum(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 1; matrix.length > 0 && i < matrix[0].length; i++) {
            matrix[0][i] += matrix[0][i - 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] += matrix[i - 1][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] += matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1];
            }
        }
       /* System.out.println(
                JsonHelper.serialize(matrix)
        );*/
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || col1 < 0 || row2 < 0 || col2 < 0 || row2 >= matrix.length || col2 >= matrix[0].length) return 0;
        if (row1 == 0 && col1 == 0) return matrix[row2][col2];
        return matrix[row2][col2] - sumRegion(0, 0, row2, col1 - 1) - sumRegion(0, 0, row1 - 1, col2)
                + sumRegion(0, 0, row1 - 1, col1 - 1);
    }

}
