package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomFlip {
    final int cols;
    final Map<Integer, Integer> matrix;
    final Random random;
    final int capacity;

    public RandomFlip(int n_rows, int n_cols) {
        this.cols = n_cols;
        this.capacity = n_rows * n_cols;
        this.matrix = new HashMap<>();
        this.random = new Random(System.currentTimeMillis());
    }

    public int[] flip() {
        Integer idx = null;
        boolean found = false;
        while (!found) {
            idx = random.nextInt(capacity);
            if (!matrix.containsKey(idx)) {
                matrix.put(idx, 1);
                found = true;
            }
        }

        int col = idx % cols;
        int row = (idx - col) / cols;

        return new int[]{row, col};
    }

    public void reset() {
        matrix.clear();
    }
}
