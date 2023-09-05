package projecteuler;

import java.util.ArrayList;

import static misc.Fibonacci.fibMemo;

public class Problem2 {
    static int MAX = 4_000_000;

    public static void main(String[] args) {
        ArrayList<Integer> sequence = new ArrayList<>();
        fibMemo(34);
        System.out.println(sequence.stream()
                .mapToInt(Integer::intValue)
                .limit(MAX)
                .filter(value -> value % 2 == 0)
                .sum());
    }
}
