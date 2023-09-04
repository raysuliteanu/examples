package misc;

import java.util.Iterator;
import java.util.PriorityQueue;

public class AndOrXor {
    public static void main(String[] args) {
//        final int[] input = new int[]{9, 6, 3, 5, 2};
//        final int[] input = new int[]{9, 8, 3, 5, 7};
        final int[] input = new int[]{76969694, 71698884, 32888447, 31877010, 65564584, 87864180, 7850891, 1505323, 17879621, 15722446};

//        final int[] input = new int[100_000];
//        Arrays.fill(input, 1);
//        final Random random = new Random();
//        for (int i = 0; i < 100_000; i++) {
//            input[i] = i + 1;
//        }
        long start = System.currentTimeMillis();
        final int result = andXorOr(input);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(result + " in " + elapsed + "ms");
    }

    static int andXorOr(int[] a) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(a.length);
        for (int i : a) {
            heap.add(i);
        }

        int max = 0;

        for (int i = 0; i < a.length - 1; i++) {
            int m1 = heap.remove();
            int m2 = heap.element();
            int val = ((m1 & m2) ^ (m1 | m2)) & (m1 ^ m2);
            max = Math.max(max, val);
            heap.add(m1);
            System.out.printf("%s = %d\n", heap, max);
            heap.remove(a[i]);
        }

        heap.clear();
        for (int i : a) {
            heap.add(i);
        }

        for (int i = a.length - 1; i > 0; i--) {
            int m1 = heap.remove();
            int m2 = heap.element();
            int val = ((m1 & m2) ^ (m1 | m2)) & (m1 ^ m2);
            max = Math.max(max, val);
            heap.add(m1);
            System.out.printf("%s = %d\n", heap, max);
            heap.remove(a[i]);
        }

        return max;
    }
}
