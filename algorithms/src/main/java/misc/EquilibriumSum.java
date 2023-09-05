package misc;

// given an array of size N of integers, find the "equilibrium" index,
// which is the index in the array where the sum of the values before the index
// and the sum of the values after the index are the same. Return -1 if no such
// index exists.
public class EquilibriumSum {
    public int equilibriumIndex(int[] A) {
        if (A.length <= 1) {
            return -1;
        }

        for (int i = 1; i < A.length - 1; i++) {
            try {
                int leftSum = sum(A, 0, i);
                int rightSum = sum(A, i + 1, A.length);

                if (leftSum == rightSum) {
                    return i;
                }
            } catch (Exception e) {
                break;
            }
        }

        return -1;
    }

    private int sum(final int[] array, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            if ((long) sum + array[i] > Integer.MAX_VALUE || (long) sum + array[i] < Integer.MIN_VALUE) {
                throw new IllegalArgumentException();
            }
            sum += array[i];
        }

        return sum;
    }
}
