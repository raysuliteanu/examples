package misc;

import java.util.HashMap;

public abstract class Fibonacci {
    public static int fib(int value) {
        if (value <= 1) {
            return value;
        }

        return fib(value - 1) + fib(value - 2);
    }

    public static int fibBU(int value) {
        if (value == 0) {
            return 0;
        }

        int previousFib = 0;
        int currentFib = 1;
        for (int i = 0; i < value - 1; i++) {
            int newFib = previousFib + currentFib;
            previousFib = currentFib;
            currentFib = newFib;
        }

        return currentFib;
    }

    static HashMap<Integer, Integer> memo = new HashMap<>();

    static {
        memo.put(0, 0);
        memo.put(1, 1);
    }

    public static int fibMemo(int value) {
        return memo.computeIfAbsent(value, n -> fibMemo(n - 1) + fibMemo(n - 2));
    }
}
