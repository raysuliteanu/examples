package projecteuler;

import java.util.stream.IntStream;

public class Problem6 {
    public static void main(String[] args) {
        int sumOfSquares = IntStream.rangeClosed(1, 100)
//                .peek(i -> System.out.printf("%d^2 -> ", i))
                .map(i -> i * i)
//                .peek(System.out::println)
                .sum();

        int sum = IntStream.rangeClosed(1, 100).sum();
        int squareOfSum = sum * sum;

        System.out.println("diff between square of sums and sum of squares: " + (squareOfSum - sumOfSquares));
    }
}
