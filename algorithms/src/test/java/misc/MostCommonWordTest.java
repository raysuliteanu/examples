package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostCommonWordTest {

    @Test
    void mostCommonWords() {
        MostCommonWord mostCommonWord = new MostCommonWord();
        String actual = mostCommonWord.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        assertEquals("ball", actual);

        actual = mostCommonWord.mostCommonWord("Bob. hIt, baLl", new String[]{"bob", "hit"});
        assertEquals("ball", actual);
    }
}