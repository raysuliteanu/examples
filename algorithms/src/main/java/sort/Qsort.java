package sort;

import java.util.Arrays;
import java.util.Random;

public class Qsort {
    public static void qsort(int[] arr) {
        qsort(arr, 0, arr.length);
    }

    private static void qsort(int[] arr, int start, int end) {
        assert start >= 0;
        assert end >= start;
        assert arr.length >= (end - start);
        if (start < end) {
            int index = partition(arr, start, end);
            qsort(arr, start, index);
            qsort(arr, index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = start;
        int left = start + 1;
        int right = end;
        while (left < right) {
            if (arr[left] <= arr[pivot]) {
                swap(arr, left, pivot);
                pivot = left;
                ++left;
            }
            else {
                swap(arr, left, right - 1);
                --right;
            }
        }

        return pivot;
    }

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    private static final Random random = new Random(System.currentTimeMillis());
    public static final int SIZE = 5_000_000;

    public static void main(String[] args) {
        int[] arr1 = randomInts();
        int[] arr2 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);

        long start = System.currentTimeMillis();
        qsort(arr1);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("mine: " + elapsed);

        start = System.currentTimeMillis();
        Arrays.sort(arr2);
        elapsed = System.currentTimeMillis() - start;
        System.out.println("JDK's: " + elapsed);
    }

    private static int[] randomInts() {
        int[] ints = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }
}
