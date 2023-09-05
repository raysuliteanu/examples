package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Fancy {
    // 10^9 + 7
    private static final int MODULO = 1_000_000_000 + 7;

    private final List<BigInteger> seq = new ArrayList<>();

    public Fancy() {
    }

    public void append(int val) {
        seq.add(BigInteger.valueOf(val));
    }

    public void addAll(int inc) {
        for (int i = 0; i < seq.size(); i++) {
            final BigInteger val = seq.get(i);
            seq.set(i, val.add(BigInteger.valueOf(inc)));
        }
    }

    public void multAll(int m) {
        for (int i = 0; i < seq.size(); i++) {
            final BigInteger val = seq.get(i);
            seq.set(i, val.multiply(BigInteger.valueOf(m)));
        }
    }

    public int getIndex(int idx) {
        if (seq.isEmpty() || idx >= seq.size()) {
            return -1;
        }

        return (int) (seq.get(idx).remainder(BigInteger.valueOf(MODULO)).longValue());
    }

    public static void main(String[] args) throws IOException {
//        String[] commands = {"append", "append", "getIndex", "append", "getIndex", "addAll", "append", "getIndex", "getIndex", "append", "append", "getIndex", "append", "getIndex", "append", "getIndex", "append", "getIndex", "multAll", "addAll", "getIndex", "append", "addAll", "getIndex", "multAll", "getIndex", "multAll", "addAll", "addAll", "append", "multAll", "append", "append", "append", "multAll", "getIndex", "multAll", "multAll", "multAll", "getIndex", "addAll", "append", "multAll", "addAll", "addAll", "multAll", "addAll", "addAll", "append", "append", "getIndex"};
//        int[] input = {12, 8, 1, 12, 0, 12, 8, 2, 2, 4, 13, 4, 12, 6, 11, 1, 10, 2, 3, 1, 6, 14, 5, 6, 12, 3, 12, 15, 6, 7, 8, 13, 15, 15, 10, 9, 12, 12, 9, 9, 9, 9, 4, 8, 11, 15, 9, 1, 4, 10, 9};

        final ClassLoader classLoader = Fancy.class.getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream("fancy.txt");
        if (inputStream == null) {
            throw new IOException("can't find fancy.txt");
        }

        List<Integer> results = new LinkedList<>();
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final String[] commands = reader.readLine().split(",");
            final int[] input = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::valueOf).toArray();
            Fancy f = new Fancy();
            for (int i = 0; i < commands.length; i++) {
                String command = commands[i];
                switch (command) {
                    case "append" -> {
                        f.append(input[i]);
                    }
                    case "getIndex" -> {
                        results.add(f.getIndex(input[i]));
                    }
                    case "addAll" -> {
                        f.addAll(input[i]);
                    }
                    case "multAll" -> {
                        f.multAll(input[i]);
                    }
                }
            }
        }
        System.out.println(results);

    }
}

