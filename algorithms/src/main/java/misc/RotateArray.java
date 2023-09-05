package misc;

import java.util.Arrays;

public class RotateArray {
    static int[] rotate(int[] A, int K) {
        if (K == 0 || K == A.length) {
            return A;
        }
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[(i + (K - 1)) % A.length];
        }

        return B;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotate(new int[]{3, 8, 9, 7, 6}, 3)));
        System.out.println(Arrays.toString(rotate(new int[]{5, -5}, 1)));
        System.out.println(Arrays.toString(rotate(new int[]{5, -5}, 2)));
    }
}
