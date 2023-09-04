package misc;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakeSentencesTest {
    private MakeSentences.TrieNode dictionary;

    @BeforeEach
    void setup() {
        dictionary = new MakeSentences.TrieNode();
        dictionary.insert("this");
        dictionary.insert("is");
        dictionary.insert("hot");
        dictionary.insert("dog");
        dictionary.insert("hotdog");
    }

    @Test
    void multiple() {
        MakeSentences makeSentences = new MakeSentences(dictionary);
        final List<String> result = makeSentences.makeSentence("thisishotdog");
        final List<String> expected = Arrays.asList("this is hot dog", "this is hotdog");
        assertEquals(expected, result);
    }
}