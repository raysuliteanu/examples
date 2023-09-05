package misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadgeTimes {
    // input is array of tuples of (user, time) where time is a string in HHmm format e.g. 1235 or 0521
    // output should be a string with user and times e.g. 'ray: 1 2 3' for any user that has badged in >= 3 times in 60 min
    static String checkForViolations(String[][] input) {
        final SimpleDateFormat timeInstance = (SimpleDateFormat) SimpleDateFormat.getTimeInstance();
        timeInstance.applyPattern("HHmm");

        Map<String, List<Date>> users = toUserMap(input, timeInstance);

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<Date>> entry : users.entrySet()) {
            final List<Date> badgeInTimes = entry.getValue();

            // only care about badge ins >= 3 in an hour so exclude any user with less than 3 badge in times
            if (badgeInTimes.size() > 2) {
                checkUser(timeInstance, builder, entry, badgeInTimes);
            }
        }

        return builder.toString();
    }

    private static void checkUser(final SimpleDateFormat timeInstance, final StringBuilder builder, final Map.Entry<String, List<Date>> entry, final List<Date> badgeInTimes) {
        Collections.sort(badgeInTimes);
        final List<Date> violations = new ArrayList<>();
        for (int i = 0; i < badgeInTimes.size() - 1; i++) {
            Date current = badgeInTimes.get(i);
            for (int j = i + 1; j < badgeInTimes.size(); j++) {
                final Date badgeTime = badgeInTimes.get(j);
                if (isWithinOneHour(current, badgeTime)) {
                    violations.add(badgeTime);
                } else {
                    break;
                }
            }

            if (violations.size() >= 2) {
                builder.append(entry.getKey()).append(": ").append(timeInstance.format(current)).append(" ");
                for (Date violation : violations) {
                    builder.append(timeInstance.format(violation)).append(" ");
                }
                builder.append("\n");
                break;
            }
        }
    }

    private static boolean isWithinOneHour(final Date current, final Date date) {
        return Duration.ofMillis(date.getTime() - current.getTime()).getSeconds() <= ChronoUnit.HOURS.getDuration().getSeconds();
    }

    private static Map<String, List<Date>> toUserMap(final String[][] input, final SimpleDateFormat timeInstance) {
        Map<String, List<Date>> users = new HashMap<>(input.length);
        for (String[] strings : input) {
            final List<Date> dates = users.computeIfAbsent(strings[0], k -> new ArrayList<>());
            try {
                dates.add(timeInstance.parse(strings[1]));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return users;
    }

    public static void main(String[] args) {
        String[][] input = {
                {"ray", "0532"},
                {"tom", "1500"},
                {"ray", "0628"},
                {"jon", "0628"},
                {"ray", "0632"},
                {"ray", "1132"},
                {"tom", "1400"},
                {"joe", "1300"},
                {"tom", "0500"},
                {"joe", "1232"},
                {"joe", "1238"},
                {"tom", "1430"},
                {"ray", "0533"},
                {"ray", "0534"},
                {"jon", "1832"}
        };

        System.out.println(BadgeTimes.checkForViolations(input));
    }
}
