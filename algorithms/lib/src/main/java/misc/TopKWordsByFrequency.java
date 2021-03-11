package misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
}
