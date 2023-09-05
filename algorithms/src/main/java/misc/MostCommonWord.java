package misc;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> bannedList = asList(banned);
        return stream(paragraph.split("[ !?',;.]+"))
                .map(String::toLowerCase)
                .filter(word -> !bannedList.contains(word))
                .collect(groupingBy(word -> word, counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(toList())
                .get(0);

    }
}
