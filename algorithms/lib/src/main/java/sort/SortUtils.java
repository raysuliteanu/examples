package sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.lang.System.arraycopy;

public abstract class SortUtils {
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            exchange(array, i, minIndex);
        }
    }

    private static void exchange(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void mergeSort(int[] array, int size) {
        if (size > 1) {
            int mid = size / 2;
            int[] left = new int[mid];
            int[] right = new int[array.length - mid];

            arraycopy(array, 0, left, 0, left.length);
            arraycopy(array, mid, right, 0, array.length - mid);

            mergeSort(left, left.length);
            mergeSort(right, right.length);

            merge(array, left, right);
        }
    }

    public static void forkJoinMergesort(int[] array, int size) {
        if (size < 1000) {
            mergeSort(array, size);
        }
        else {
            ForkJoinPool.commonPool().invoke(new ForkJoinMerge(array, size));
        }
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

    private static class ForkJoinMerge extends RecursiveAction {

        private final int[] array;
        private final int size;

        public ForkJoinMerge(final int[] array, final int size) {
            this.array = array;
            this.size = size;
        }

        @Override
        protected void compute() {
            if (size > 1) {
                int mid = size / 2;
                int[] left = new int[mid];
                int[] right = new int[array.length - mid];

                arraycopy(array, 0, left, 0, left.length);
                arraycopy(array, mid, right, 0, array.length - mid);

                var leftFork = new ForkJoinMerge(left, left.length);
                var rightFork = new ForkJoinMerge(right, right.length);

                leftFork.fork();
                rightFork.fork();

                leftFork.join();
                rightFork.join();

                merge(array, left, right);
            }
        }
    }
}
