package misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
        Map<String, Tuple> popularity = new HashMap<>(features.length);
        for (int i = 0, featuresLength = features.length; i < featuresLength; i++) {
            final String feature = features[i];
            int count = 0;
            for (String response : responses) {
                for (String word : pattern.splitAsStream(response).distinct().collect(toList())) {
                    if (feature.equals(word)) {
                        ++count;
                    }
                }
            }
            popularity.put(feature, Tuple.of(count, i));
        }

        // if same count, sort/order by position in features[]
        return popularity.entrySet().stream()
                .sorted((o1, o2) -> {
                    final Tuple value1 = o1.getValue();
                    final Tuple value2 = o2.getValue();
                    final int countCompare = Integer.compare(value2.count, value1.count);
                    return countCompare != 0 ? countCompare : Integer.compare(value1.index, value2.index);
                })
                .map(Map.Entry::getKey)
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
