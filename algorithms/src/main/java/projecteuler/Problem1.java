package projecteuler;

import java.util.stream.IntStream;

public class Problem1 {
    public static void main(String[] args) {
        System.out.println(IntStream.range(1, 1000)
                .filter(value -> value % 3 == 0 || value % 5 == 0)
                .sum());
    }
}
