package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostVisited {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Event>> eventsByUser = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            final List<Event> events = eventsByUser.computeIfAbsent(username[i], k -> new ArrayList<>(3));
            events.add(new Event(timestamp[i], username[i], website[i]));
        }

        Map<String, Integer> counts = new HashMap<>();
        for (String user : eventsByUser.keySet()) {
            final List<Event> events = eventsByUser.get(user);
            Collections.sort(events);
            for (int i = 0; i < events.size() - 2; i++) {
                for (int j = i + 1; j < events.size() - 1; j++) {
                    for (int k = j + 1; k < events.size(); k++) {
                        final String first = events.get(i).website;
                        final String second = events.get(j).website;
                        final String third = events.get(k).website;
                        final String key = first + "|" + second + "|" + third;
                        final boolean allSameSite = first.equals(second) && second.equals(third);
                        final boolean lastSameSite = !first.equals(second) && second.equals(third);
                        if ((allSameSite || lastSameSite) && counts.containsKey(key)) {
                            continue;
                        }
                        counts.put(key, counts.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }

        int max = counts.values().stream().max(Integer::compareTo).get();

        final List<List<String>> result = counts.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey().split("\\|"))
                .map(Arrays::asList)
                .collect(Collectors.toList());
        return result.get(0);
    }

    static class Event implements Comparable<Event> {
        int ts;
        String user;
        String website;

        public Event(final int ts, final String user, final String website) {
            this.ts = ts;
            this.user = user;
            this.website = website;
        }

        @Override
        public int compareTo(final Event event) {
            return Integer.compare(ts, event.ts);
        }
    }
}
