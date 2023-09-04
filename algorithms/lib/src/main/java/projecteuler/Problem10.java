package projecteuler;

import static projecteuler.Problem7.isPrime;

public class Problem10 {
    public static void main(String[] args) {
        long sum = 2;
        long primeCandidate = 3;

        long start = System.currentTimeMillis();
        while (primeCandidate < 2_000_000) {
            if (isPrime(primeCandidate)) {
                sum += primeCandidate;
            }

            primeCandidate += 2;
        }
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("elapsed = " + elapsed  + "ms");
        System.out.println(sum);
        // 142913828922
    }
}
