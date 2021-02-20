package misc;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopKWordsByFrequencyTest {
    @Test
    void testTopK() {
        final TopKWordsByFrequency wordsByFrequency = new TopKWordsByFrequency();
        assertEquals(asList("i", "love"), wordsByFrequency.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        assertEquals(asList("the", "is", "sunny", "day"), wordsByFrequency.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        assertTrue(wordsByFrequency.topKFrequent(new String[]{}, 1).isEmpty());
    }
}