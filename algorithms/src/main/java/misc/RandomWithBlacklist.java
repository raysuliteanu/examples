package misc;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWithBlacklist {
    final Set<Integer> blacklist;
    final int max;
    final Random random;

    public RandomWithBlacklist(int N, int[] blacklist) {
        this.blacklist = new HashSet<>();
        for (Integer val : blacklist) {
            this.blacklist.add(val);
        }
        this.max = N;
        this.random = new Random(System.currentTimeMillis());
    }

    public int pick() {
        int result;
        do {
            result = random.nextInt(max);
        } while (blacklist.contains(result));

        return result;
    }
}
