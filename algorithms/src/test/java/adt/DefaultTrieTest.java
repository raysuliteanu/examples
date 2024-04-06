package adt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTrieTest {
    @Test
    public void findInEmptyTrie() {
        final DefaultTrie defaultTrie = new DefaultTrie();
        assertFalse(defaultTrie.find("missing"));
    }

    @Test
    public void insertAndFind() {
        final DefaultTrie defaultTrie = new DefaultTrie();
        defaultTrie.insert("hello");
        assertTrue(defaultTrie.find("hello"));
        assertFalse(defaultTrie.find("hello world"));
        assertFalse(defaultTrie.find("hell"));
    }

    @Test
    public void removeAndFind() {
        final DefaultTrie defaultTrie = new DefaultTrie();
        defaultTrie.delete("missing"); // no failure
        defaultTrie.insert("hello");
        assertTrue(defaultTrie.find("hello"));
        defaultTrie.delete("hell"); // no effect since not a complete word
        assertTrue(defaultTrie.find("hello"));
        defaultTrie.delete("hello");
        assertFalse(defaultTrie.find("hello"));
    }

    @Test
    public void nullWordInsert() {
        assertThrows(IllegalArgumentException.class, () -> new DefaultTrie().insert(null));
    }

    @Test
    public void nullWordDelete() {
        assertThrows(IllegalArgumentException.class, () -> new DefaultTrie().delete(null));
    }

    @Test
    public void nullWordMatches() {
        assertThrows(IllegalArgumentException.class, () -> new DefaultTrie().matches(null));
    }

    @Test
    public void findEmptyWordFindsNothing() {
        assertFalse(new DefaultTrie().find(""));
    }

    @Test
    public void matchesEmptyWordMatchesNothing() {
        assertEquals(Collections.emptyList(), new DefaultTrie().matches(""));
    }

    @Test
    public void matches() {
        final DefaultTrie defaultTrie = new DefaultTrie();
        defaultTrie.insert("hello");
        defaultTrie.insert("hell");
        defaultTrie.insert("help");
        defaultTrie.insert("hi");
        defaultTrie.insert("he");
        defaultTrie.insert("heap");
        defaultTrie.insert("world");
        defaultTrie.insert("word");
        defaultTrie.insert("would");
        defaultTrie.insert("wound");
        defaultTrie.insert("wounded");

        List<String> matches = defaultTrie.matches("he");

        List<String> expected = Arrays.asList("hello", "hell", "help", "he", "heap");
        assertTrue(matches.containsAll(expected), matches.toString());

        matches = defaultTrie.matches("wor");

        expected = Arrays.asList("word", "world");
        assertTrue(matches.containsAll(expected), matches.toString());

        matches = defaultTrie.matches("wou");

        expected = Arrays.asList("would", "wound", "wounded");
        assertTrue(matches.containsAll(expected), matches.toString());
    }
}