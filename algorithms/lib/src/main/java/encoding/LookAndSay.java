package encoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// see https://en.wikipedia.org/wiki/Look-and-say_sequence
public class LookAndSay {
    public List<String> generate(int iterations) {
        List<String> output = new ArrayList<>();
        output.add("1");
        return generate(output, "1", iterations - 1);
    }

    public List<String> generate(String input, int iterations) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> output = new ArrayList<>();
        output.add(input);
        return generate(output, input, iterations - 1);
    }

    private List<String> generate(List<String> output, String input, int iterations) {
        if (iterations > 0) {
            char[] chars = input.toCharArray();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < chars.length; ) {
                char aChar = chars[i];
                if (!Character.isDigit(aChar)) {
                    throw new IllegalArgumentException(input);
                }
                int anInt = aChar - '0';
                int count = 0;
                do {
                    ++count;
                } while (++i < chars.length && anInt == (chars[i] - '0'));
                result.append(count).append(anInt);
            }
            String value = result.toString();
            output.add(value);
            generate(output, value, iterations - 1);
        }
        return output;
    }
}
