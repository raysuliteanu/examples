package misc;

import java.util.concurrent.ConcurrentHashMap;

public class Factorial {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private static final ConcurrentHashMap<Integer, Integer> memo = new ConcurrentHashMap<>();

    static {
        memo.put(0, 1);
    }

    public static int factorialMemo(int n) {
        if (n == 0) {
            return 1;
        }

        return n * memo.computeIfAbsent(n - 1, Factorial::factorialMemo);
    }
}
