package misc;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGeneratorFactory;

/*
    Given two non-empty, sorted arrays with distinct elements in each array (no dupes in an array), return
    a count of the elements in common. For example,
    in1: [1, 2, 3, 4]
    in2: [2, 4, 6, 8]
    out: 2 (2 and 4 are in common)
 */
public class ArrayElementsInCommon {
    private static int countCommon(int[] in1, int[] in2) {
        int count = 0;
        int idx1 = 0;
        int idx2 = 0;

        while (idx1 < in1.length && idx2 < in2.length) {
            if (in1[idx1] == in2[idx2]) {
                ++count;
                ++idx1;
                ++idx2;
            }
            else if (in1[idx1] < in2[idx2]) {
                ++idx1;
            }
            else {
                ++idx2;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Random random = Random.from(RandomGeneratorFactory.getDefault().create());

        for (int i = 0; i < 5; i++) {
            System.out.println("iteration " + (i + 1));
            int[] in1 = generateInput(random);
            int[] in2 = generateInput(random);

            System.out.println(in1.length + ":" + Arrays.toString(in1));
            System.out.println(in2.length + ":" + Arrays.toString(in2));
            System.out.println(countCommon(in1, in2));
        }

    }

    private static int[] generateInput(Random random) {
        return random.ints(20)
                .map(i -> Math.abs(i) % 100)
                .distinct()
                .sorted()
                .toArray();
    }
}
