package misc;

import java.util.Arrays;

public class MaxConsecutive {
    public static int maxConsecutiveInteger(int[] input) {
        assert input != null : "input must not be null";

        if (input.length == 0) {
            return 0;
        }

        if (input.length == 1) {
            return 1;
        }

        int[] data = new int[input.length];
        System.arraycopy(input, 0, data, 0, input.length);
        Arrays.sort(data);

        int maxConsecutive = 1;
        int consecutive = 1;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] + 1 == data[i + 1]) {
                ++consecutive;
            } else {
                maxConsecutive = Math.max(maxConsecutive, consecutive);
                consecutive = 1;
            }
        }

        maxConsecutive = Math.max(maxConsecutive, consecutive);

        return maxConsecutive;
    }
}
