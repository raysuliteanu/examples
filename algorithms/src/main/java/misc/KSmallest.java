package misc;

import java.util.stream.Stream;

public class KSmallest {
    public int findKthNumber(int n, int k) {

        final String value = Stream.iterate(1, integer -> integer + 1)
                .limit(n)
                .map(String::valueOf)
                .sorted()
                .skip(k - 1)
                .limit(1)
                .toList()
                .get(0);

        return Integer.parseInt(value);
    }
}
