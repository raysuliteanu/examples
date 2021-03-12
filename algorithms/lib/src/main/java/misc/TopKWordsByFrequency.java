package misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Long.compare;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.toList;

public class TopKWordsByFrequency {
    public List<String> topKFrequent(String[] words, int k) {
        return Arrays.stream(words)
                .collect(groupingByConcurrent(word -> word, counting()))
                .entrySet().stream()
                .sorted((o1, o2) -> {
                    final int compareCount = compare(o2.getValue(), o1.getValue());
                    // if same count, sort/order by word
                    return compareCount != 0 ? compareCount : o1.getKey().compareTo(o2.getKey());
                })
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    public String[] sortFeatures(String[] features, String[] responses) {
        Pattern pattern = Pattern.compile(" ");
        Tuple[] popularity = new Tuple[features.length];
        for (int i = 0, featuresLength = features.length; i < featuresLength; i++) {
            final String feature = features[i];
            int count = 0;
            for (String response : responses) {
                Map<String, String> seen = new HashMap<>();
                for (String word : pattern.split(response)) {
                    if (!seen.containsKey(word) && feature.equals(word)) {
                        ++count;
                    }
                    seen.put(word, word);
                }
            }
            popularity[i] = Tuple.of(count, i);
        }

        return Stream.of(popularity)
                .sorted((t1, t2) -> {
                    final int comparedCount = Integer.compare(t2.count, t1.count);
                    return comparedCount != 0 ? comparedCount : Integer.compare(t1.index, t2.index);
                })
                .map(t -> features[t.index])
                .toArray(String[]::new);
    }

    private static class Tuple {
        final int count;
        final int index;

        private Tuple(final int count, final int index) {
            this.count = count;
            this.index = index;
        }

        static Tuple of(int count, int index) {
            return new Tuple(count, index);
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "count=" + count +
                    ", index=" + index +
                    '}';
        }
    }
}
