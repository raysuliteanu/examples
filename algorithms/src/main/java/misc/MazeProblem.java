package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given a grid NxM, how many paths does exist for a rabbit in the top left
 * to get to the bottom right if it can only move right or down?
 * <p>
 * e.g.
 * paths(18, 6) == 26334
 * paths(75, 19) == 5873182941643167150
 * <p>
 * Solution: use Dynamic Programming i.e. memoization to remember that a grid
 * of a certain dimension is made up of grids of smaller dimensions given the
 * movement is constrained to right and down.
 * <p>
 * While this could be done with recursion, the solution here primes a memoized
 * table, and then looks up the result.
 */
public class MazeProblem {

    public static long paths(int n, int m) {
        assert n > 0 : "n must be > 0";
        assert m > 0 : "m must be > 0";

        Map<Tuple, Long> memo = new HashMap<>();

        for (int i = 1; i < n + 1; i++) {
            memo.put(Tuple.of(i, 1), 1L);
        }

        for (int i = 1; i < m + 1; i++) {
            memo.put(Tuple.of(1, i), 1L);
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < m + 1; j++) {
                memo.put(Tuple.of(i, j), memo.get(Tuple.of(i - 1, j)) + memo.get(Tuple.of(i, j - 1)));
            }
        }

        return memo.get(Tuple.of(n, m));
    }

    private static class Tuple {
        int n;
        int m;

        public Tuple(int n, int m) {
            this.n = n;
            this.m = m;
        }

        static Tuple of(int n, int m) {
            return new Tuple(n, m);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return n == tuple.n && m == tuple.m;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, m);
        }
    }
}
