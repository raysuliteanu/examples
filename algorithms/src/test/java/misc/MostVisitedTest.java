package misc;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostVisitedTest {
    @Test
    void setOne() {
        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
        final MostVisited mostVisited = new MostVisited();
        final List<String> result = mostVisited.mostVisitedPattern(username, timestamp, website);
        final List<String> expected = Arrays.asList("home", "about", "career");
        assertEquals(expected, result);
    }

    @Test
    void setOnePrime() {
        String[] username = {"joe", "james", "joe", "joe", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = {"home", "home", "about", "career", "cart", "maps", "home", "home", "about", "career"};
        final MostVisited mostVisited = new MostVisited();
        final List<String> result = mostVisited.mostVisitedPattern(username, timestamp, website);
        final List<String> expected = Arrays.asList("home", "about", "career");
        assertEquals(expected, result);
    }

    @Test
    void setTwo() {
        String[] username = {"zkiikgv", "zkiikgv", "zkiikgv", "zkiikgv"};
        int[] timestamp = {436363475, 710406388, 386655081, 797150921};
        String[] website = {"wnaaxbfhxp", "mryxsjc", "oz", "wlarkzzqht"};
        final MostVisited mostVisited = new MostVisited();
        final List<String> result = mostVisited.mostVisitedPattern(username, timestamp, website);
        final List<String> expected = Arrays.asList("oz", "mryxsjc", "wlarkzzqht");
        assertEquals(expected, result);
    }

    @Test
    void setThree() {
        String[] username = {"lpgbr", "by", "by", "lpgbr", "by", "ditctqnahs", "by"};
        int[] timestamp = {117853717, 760542754, 858167998, 235286033, 992196098, 273717872, 792447849};
        String[] website = {"inc", "inc", "inc", "ftd", "inc", "ftd", "inc"};
        final MostVisited mostVisited = new MostVisited();
        final List<String> result = mostVisited.mostVisitedPattern(username, timestamp, website);
        final List<String> expected = Arrays.asList("inc", "inc", "inc");
        assertEquals(expected, result);
    }

    @Test
    void setFour() {
        String[] username = {"h", "eiy", "cq", "h", "cq", "txldsscx", "cq", "txldsscx", "h", "cq", "cq"};
        int[] timestamp = {527896567, 334462937, 517687281, 134127993, 859112386, 159548699, 51100299, 444082139, 926837079, 317455832, 411747930};
        String[] website = {"hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "yljmntrclw", "hibympufi", "yljmntrclw"};
        final MostVisited mostVisited = new MostVisited();
        final List<String> result = mostVisited.mostVisitedPattern(username, timestamp, website);
        final List<String> expected = Arrays.asList("hibympufi", "hibympufi", "yljmntrclw");
        assertEquals(expected, result);
    }
}