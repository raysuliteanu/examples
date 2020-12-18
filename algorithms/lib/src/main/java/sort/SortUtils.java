package sort;

import static java.lang.System.arraycopy;

public abstract class SortUtils {
    public static void mergeSort(int[] array, int size) {
        if (size < 2) { return; }

        int mid = size / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        arraycopy(array, 0, left, 0, left.length);

        for (int i = 0, j = mid; j < array.length; i++, j++) {
            right[i] = array[j];
        }

        mergeSort(left, left.length);
        mergeSort(right, right.length);
        merge(array, left, right);
    }

    private static void merge(final int[] dest, final int[] left, final int[] right) {
        int destIdx = 0, leftIdx = 0, rightIdx = 0;
        while (leftIdx < left.length && rightIdx < right.length) {
            if (left[leftIdx] < right[rightIdx]) {
                dest[destIdx++] = left[leftIdx++];
            }
            else {
                dest[destIdx++] = right[rightIdx++];
            }
        }

        for (int i = leftIdx; i < left.length; i++) {
            dest[destIdx++] = left[i];
        }

        for (int i = rightIdx; i < right.length; i++) {
            dest[destIdx++] = right[i];
        }
    }
}
