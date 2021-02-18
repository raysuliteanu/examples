package sort;

import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.lang.System.arraycopy;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.Arrays.parallelSort;
import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static sort.SortUtils.forkJoinMergesort;
import static sort.SortUtils.insertionSort;
import static sort.SortUtils.mergeSort;
import static sort.SortUtils.selectionSort;

public class SortUtilsTest {
    @Test
    public void merge() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeOneElement() {
        int[] actual = {5};
        int[] expected = {5};
        mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeEmpty() {
        int[] actual = {};
        int[] expected = {};
        mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void forkJoinMergeShortCircuit() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void forkJoinMerge() {
        int size = 2_000_000;
        int[] original = generateInts(size);

        int[] toBeSorted = new int[size];
        arraycopy(original, 0, toBeSorted, 0, size);

        forkJoinMergesort(toBeSorted, 1000);
        parallelSort(original); // uses optimized quicksort

        assertArrayEquals(original, toBeSorted);
    }

    @Test
    @Disabled
    public void forkJoinMergeWithTiming() {
        int size = 5_000_000;
        int[] toBeSorted = new int[size];
        int[] toBeSortedFJ = new int[size];
        for (int i = 0; i < 10; i++) {
            int[] original = generateInts(size);
            arraycopy(original, 0, toBeSorted, 0, size);
            arraycopy(original, 0, toBeSortedFJ, 0, size);

            long start = currentTimeMillis();
            mergeSort(toBeSorted);
            long elapsed = currentTimeMillis() - start;
            out.print("mergesort: " + elapsed + "ms\t");

            start = currentTimeMillis();
            forkJoinMergesort(toBeSortedFJ, 1000);
            elapsed = currentTimeMillis() - start;
            out.print("forkJoinMerge: " + elapsed + "ms\t");

            start = currentTimeMillis();
            parallelSort(original);
            elapsed = currentTimeMillis() - start;
            out.println("Arrays.parallelSort(): " + elapsed + "ms");
        }
    }

    @Test
    public void testSelectionSort() {
        final int[] ints = generateInts(100);
        final int[] sorted = new int[ints.length];
        arraycopy(ints, 0, sorted, 0, ints.length);
        sort(sorted);
        selectionSort(ints);
        assertArrayEquals(sorted, ints);
    }

    @Test
    public void testInsertionSort() {
        final int[] ints = generateInts(100);
        final int[] sorted = new int[ints.length];
        arraycopy(ints, 0, sorted, 0, ints.length);
        sort(sorted);
        insertionSort(ints);
        assertArrayEquals(sorted, ints);
    }

    private int[] generateInts(final int size) {
        Random random = new Random(currentTimeMillis());
        int[] original = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = Math.abs(random.nextInt(10_000));
        }
        return original;
    }

}
