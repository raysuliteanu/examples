package projecteuler;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Problem5 {
    public static void main(String[] args) {
        boolean done = false;
        final AtomicInteger number = new AtomicInteger(20);
        while (!done) {
            done = IntStream.rangeClosed(1, 20)
                    .allMatch(val -> number.get() % val == 0);

            if (!done) {
                number.incrementAndGet();
            }
        }

        System.out.println("smallest number evenly divisible by all n in [1..20]: " + number);
    }
}
