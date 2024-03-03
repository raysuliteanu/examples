package misc;

import java.util.stream.Stream;

public class KSmallest {
    public int findKthNumber(int n, int k) {

        return Stream.iterate(1, integer -> integer + 1)
                .limit(n)
                .map(String::valueOf)
                .sorted()
                .skip(k - 1)
                .limit(1)
                .map(Integer::valueOf)
                .toList()
                .getFirst();
    }
}
