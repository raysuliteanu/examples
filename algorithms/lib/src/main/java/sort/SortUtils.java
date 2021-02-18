package sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.lang.System.arraycopy;

public abstract class SortUtils {
    public static void bubbleSort(int[] array) {
        // for each element
        for (int i = 0; i < array.length; i++) {
            // from end to beginning of the array
            for (int j = array.length - 1; j > i; j--) {
                // "bubble" up smaller elements towards the front
                // after each pass the left side is more and more sorted,
                // hence 'i' iterates left to right and bounds 'j'
                if (array[j] < array[j - 1]) {
                    exchange(array, j - 1, j);
                }
            }
        }
    }

    public static void insertionSort(int[] array) {

        // initialize by moving smallest element to position 0 as a 'sentinel'
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] > array[i]) {
                exchange(array, i, i - 1);
            }
        }

        // for each remaining value
        for (int i = 2; i < array.length; i++) {
            var j = i;
            var value = array[i];

            // find the position to insert 'value' while shifting left intervening values
            while (value < array[j - 1]) {
                array[j] = array[j - 1];
                --j;
            }

            // and finally inserting value into its correct spot
            array[j] = value;
        }
    }

    public static void selectionSort(int[] array) {
        // for each element
        for (int i = 0; i < array.length; i++) {
            // find the index of the next smallest element remaining
            var minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // and swap with the current element
            exchange(array, i, minIndex);
        }
    }

    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            // divide & conquer by first finding the midpoint and creating two arrays for the sub-problems
            var mid = array.length / 2;
            var left = new int[mid];
            var right = new int[array.length - mid];

            arraycopy(array, 0, left, 0, left.length);
            arraycopy(array, mid, right, 0, array.length - mid);

            // recur on each sub-problem
            mergeSort(left);
            mergeSort(right);

            // merge the results of sorting the sub-problems
            merge(array, left, right);
        }
    }

    /**
     * an example of using the Java Fork-Join framework applied to the mergesort.
     * This is not necessarily meant to be more performant, but rather just an example.
     */
    public static void forkJoinMergesort(int[] array, final int threshold) {
        if (array.length < threshold) {
            mergeSort(array);
        }
        else {
            ForkJoinPool.commonPool().invoke(new ForkJoinMerge(array));
        }
    }

    private static void exchange(final int[] array, final int i, final int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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

        for (var i = leftIdx; i < left.length; i++) {
            dest[destIdx++] = left[i];
        }

        for (var i = rightIdx; i < right.length; i++) {
            dest[destIdx++] = right[i];
        }
    }

    private static class ForkJoinMerge extends RecursiveAction {
        private final int[] array;

        public ForkJoinMerge(final int[] array) {
            this.array = array;
        }

        @Override
        protected void compute() {
            if (array.length > 1) {
                var mid = array.length / 2;
                var left = new int[mid];
                var right = new int[array.length - mid];

                arraycopy(array, 0, left, 0, left.length);
                arraycopy(array, mid, right, 0, array.length - mid);

                var leftFork = new ForkJoinMerge(left);
                var rightFork = new ForkJoinMerge(right);

                leftFork.fork();
                rightFork.fork();

                leftFork.join();
                rightFork.join();

                merge(array, left, right);
            }
        }
    }
}
