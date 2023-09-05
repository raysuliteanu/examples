package misc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcatenatedWordsTest {
    @Test
    void setOne() {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        String[] expected = {"catsdogcats", "dogcatsdog", "ratcatdogcat"};

        final ConcatenatedWords concatenatedWords = new ConcatenatedWords();
        assertEquals(Arrays.asList(expected), concatenatedWords.findAllConcatenatedWordsInADict(words));
    }

    @Test
    void setTwo() {
        String[] words = {"a", "b", "ab", "abc"};
        String[] expected = {"ab"};

        final ConcatenatedWords concatenatedWords = new ConcatenatedWords();
        assertEquals(Arrays.asList(expected), concatenatedWords.findAllConcatenatedWordsInADict(words));
    }
}