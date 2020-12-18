package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
}
