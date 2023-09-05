package projecteuler;

import java.util.ArrayList;
import java.util.List;

public class Problem3 {
    public static void main(String[] args) {
        long prime = 600_851_475_143L;

        List<Long> primes = new ArrayList<>();
        long largest = primes(prime, primes);

        System.out.println(primes);
        System.out.println(largest);
    }

    private static long primes(long prime, final List<Long> primes) {
        long largest = 0;
        for (long i = 2; i < prime; i++) {
            while (prime % i == 0) {
                primes.add(i);
                largest = Math.max(i, largest);
                prime = prime / i;
            }
        }

        if (prime > 2) {
            primes.add(prime);
            largest = prime;
        }
        return largest;
    }
}
