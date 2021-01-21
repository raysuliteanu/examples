package sort;

import java.util.Random;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.parallelSort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static sort.SortUtils.forkJoinMergesort;
import static sort.SortUtils.mergeSort;

public class SortUtilsTest {
    @Test
    public void merge() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeOneElement() {
        int[] actual = {5};
        int[] expected = {5};
        mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeEmpty() {
        int[] actual = {};
        int[] expected = {};
        mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void forkJoinMergeShortCircuit() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void forkJoinMerge() {
        int size = 2_000_000;
        int[] original = generateInts(size);

        int[] toBeSorted = new int[size];
        System.arraycopy(original, 0, toBeSorted, 0, size);

        forkJoinMergesort(toBeSorted, size);
        parallelSort(original); // uses optimized quicksort

        assertArrayEquals(original, toBeSorted);
    }

    @Test
    public void forkJoinMergeWithTiming() {
        int size = 2_000_000;
        int[] original = generateInts(size);

        int[] toBeSortedFJ = new int[size];
        System.arraycopy(original, 0, toBeSortedFJ, 0, size);

        long start = System.currentTimeMillis();
        forkJoinMergesort(toBeSortedFJ, size);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("forkJoinMerge: " + elapsed + "ms");

        start = System.currentTimeMillis();
        parallelSort(original);
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Arrays.parallelSort(): " + elapsed + "ms");

        assertArrayEquals(original, toBeSortedFJ);
    }

    private int[] generateInts(final int size) {
        Random random = new Random(System.currentTimeMillis());
        int[] original = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = random.nextInt();
        }
        return original;
    }

}
