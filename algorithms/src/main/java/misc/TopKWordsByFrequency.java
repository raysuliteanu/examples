package misc;

import java.util.*;

import static java.lang.Long.compare;
import static java.util.stream.Collectors.*;

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
        Map<String, Integer> popularity = new HashMap<>();
        for (String feature : features) {
            popularity.put(feature, 0);
        }

        for (String response : responses) {
            Set<String> seen = new HashSet<>(Arrays.asList(response.split(" ")));
            for (String s : seen) {
                if (popularity.containsKey(s)) {
                    popularity.put(s, popularity.get(s) + 1);
                }
            }
        }

        Arrays.sort(features, (v1, v2) -> popularity.get(v2) - popularity.get(v1));

        return features;
    }
}
