package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianTwoArraysTest {

    MedianTwoArrays medianTwoArrays = new MedianTwoArrays();

    @Test
    void even() {
        assertEquals(1.5d, medianTwoArrays.findMedianSortedArrays(new int[] {1}, new int[]{2}));
        assertEquals(2.5d, medianTwoArrays.findMedianSortedArrays(new int[] {1,3}, new int[]{2,4}));
        assertEquals(3.5d, medianTwoArrays.findMedianSortedArrays(new int[] {1,3,5}, new int[]{2,4,6}));
    }

    @Test
    void odd() {
        assertEquals(1.0, medianTwoArrays.findMedianSortedArrays(new int[] {1}, new int[]{}));
        assertEquals(2.0, medianTwoArrays.findMedianSortedArrays(new int[] {1,3}, new int[]{2}));
        assertEquals(3.0, medianTwoArrays.findMedianSortedArrays(new int[] {1,3,5}, new int[]{2,4}));
    }
}