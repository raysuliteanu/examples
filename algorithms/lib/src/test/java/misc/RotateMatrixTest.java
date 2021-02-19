package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RotateMatrixTest {
    @Test
    public void rotate() {
        RotateMatrix rotateMatrix = new RotateMatrix();
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotateMatrix.rotateRight90(matrix1);

        int[][] expected1 = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        verify(matrix1, expected1);

        int[][] matrix2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotateMatrix.rotateRight90(matrix2);

        int[][] expected2 = {{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}};
        verify(matrix2, expected2);
    }

    private void verify(final int[][] matrix, final int[][] expected) {
        for (int i = 0; i < matrix.length; i++) {
            int[] ints = matrix[i];
            assertArrayEquals(expected[i], ints);
        }
    }
}