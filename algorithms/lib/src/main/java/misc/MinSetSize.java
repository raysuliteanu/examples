package misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MinSetSize {
    public int minSetSize(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }

        Map<Integer, Integer> counts = new HashMap<>();

        for (int val : arr) {
            if (counts.containsKey(val)) {
                counts.put(val, counts.get(val) + 1);
            }
            else {
                counts.put(val, 1);
            }
        }

        int setSize = 1;
        int sum = 0;

        final List<Integer> list = counts.values().stream().sorted((v1, v2) -> Integer.compare(v2, v1)).collect(Collectors.toList());
        for (Integer count : list) {
            sum += count;
            if (sum >= arr.length / 2) {
                break;
            }
            else {
                ++setSize;
            }
        }

        return setSize;
    }

}
