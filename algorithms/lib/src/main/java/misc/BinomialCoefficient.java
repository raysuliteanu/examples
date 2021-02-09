package misc;

import java.util.concurrent.ConcurrentHashMap;

import static misc.Factorial.factorial;

public class BinomialCoefficient {
    private static final ConcurrentHashMap<Integer, Integer> memo = new ConcurrentHashMap<>();

    public static int binomialCoefficientMemoized(int n, int k) {
        if (n == 0 || n == k) {
            return 1;
        }

        return memo.computeIfAbsent(n, Factorial::factorial) /
                (memo.computeIfAbsent(k, Factorial::factorial) * memo.computeIfAbsent(n - k, Factorial::factorial));
    }

    public static int binomialCoefficient(int n, int k) {
        if (n == 0 || n == k) {
            return 1;
        }

        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}
