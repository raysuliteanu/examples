package misc;

public class RotateMatrix {

    void rotateRight90(int[][] matrix) {
        final int length = matrix.length;

        if (length == 1) {
            return;
        }

        for (int x = 0; x < length / 2; x++) {
            for (int y = x; y < length - 1 - x; y++) {
                int top = matrix[x][y];

                // top = left
                matrix[x][y] = matrix[length - 1 - y][x];

                // left = bottom
                matrix[length - 1 - y][x] = matrix[length - 1 - x][length - 1 - y];

                // bottom = right
                matrix[length - 1 - x][length - 1 - y] = matrix[y][length - 1 - x];

                // right = top
                matrix[y][length - 1 - x] = top;
            }
        }
    }
}
