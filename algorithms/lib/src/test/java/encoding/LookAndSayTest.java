package encoding;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// see https://en.wikipedia.org/wiki/Look-and-say_sequence
public class LookAndSayTest {
    @Test
    void lookAndSayDefault() {
        List<String> expected = Arrays.asList(
                "1",
                "11",
                "21",
                "1211",
                "111221",
                "312211",
                "13112221",
                "1113213211",
                "31131211131221",
                "13211311123113112211");
        LookAndSay lookAndSay = new LookAndSay();
        List<String> output = lookAndSay.generate(expected.size());
        assertIterableEquals(expected, output);
    }

    @Test
    void lookAndSay() {
        List<String> expected = Arrays.asList(
                "31131211131221",
                "13211311123113112211",
                "11131221133112132113212221",
                "3113112221232112111312211312113211");
        LookAndSay lookAndSay = new LookAndSay();
        List<String> output = lookAndSay.generate("31131211131221", expected.size());
        assertIterableEquals(expected, output);
    }

    @Test
    void emptyInput() {
        assertTrue(new LookAndSay().generate("", 666).isEmpty());
    }

    @Test
    void nullInput() {
        assertTrue(new LookAndSay().generate(null, 666).isEmpty());
    }
}
