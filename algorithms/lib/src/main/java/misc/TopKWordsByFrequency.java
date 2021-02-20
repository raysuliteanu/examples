package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Long.compare;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.toList;

public class TopKWordsByFrequency {
    public List<String> topKFrequent(String[] words, int k) {

        final List<Tuple> list = Arrays.stream(words)
                .collect(groupingByConcurrent(word -> word, counting()))
                .entrySet().parallelStream()
                .map(entry -> Tuple.of(entry.getValue(), entry.getKey()))
                .sorted()
                .collect(toList());

        final List<String> topK = new ArrayList<>(k);
        for (int i = 0; i < k && !list.isEmpty(); i++) {
            topK.add(list.remove(0).word);
        }

        return topK;
    }

    private static class Tuple implements Comparable<Tuple> {
        long count;
        String word;

        public Tuple(long count, String word) {
            this.count = count;
            this.word = word;
        }

        public static Tuple of(long count, String word) {
            return new Tuple(count, word);
        }

        @Override
        public int compareTo(final Tuple other) {
            final int compareCount = compare(other.count, count);
            // if same count, sort/order by word
            return compareCount != 0 ? compareCount : word.compareTo(other.word);
        }
    }
}
