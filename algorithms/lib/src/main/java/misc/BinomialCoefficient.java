package misc;

import java.util.HashMap;

import static misc.Factorial.factorial;

public class BinomialCoefficient {
    private static final HashMap<Integer, Integer> memo = new HashMap<>();

    public static int binomialCoefficientMemoized(int n, int k) {
        if (n == 0 || n == k) {
            return 1;
        }

        return memo.computeIfAbsent(n, Factorial::factorialMemo) /
                (memo.computeIfAbsent(k, Factorial::factorialMemo) * memo.computeIfAbsent(n - k, Factorial::factorialMemo));
    }

    public static int binomialCoefficient(int n, int k) {
        if (n == 0 || n == k) {
            return 1;
        }

        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}
