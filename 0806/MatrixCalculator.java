
public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };
        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("Matrix Addition:");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("\nMatrix Multiplication:");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        System.out.println("\nMatrix Transpose (A):");
        printMatrix(transposeMatrix(matrixA));

        System.out.println("\nMax and Min in MatrixA:");
        int[] maxMin = findMaxMin(matrixA);
        System.out.println("Max: " + maxMin[0] + ", Min: " + maxMin[1]);
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length, cols = a[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length, colsA = a[0].length, colsB = b[0].length;
        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transposeMatrix(int[][] a) {
        int rows = a.length, cols = a[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    public static int[] findMaxMin(int[][] a) {
        int max = a[0][0], min = a[0][0];
        for (int[] row : a) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
                if (val < min) {
                    min = val;
                }
            }
        }
        return new int[]{max, min};
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
