package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static java.lang.String.join;
import static java.util.Objects.requireNonNull;

/*
You are given a english dictionary and a sequence of characters without word separated,
task is to make sentence(s) out of given character sequence

Input: thisisdog => Output: ["this is dog"]
Input: thisishotdog => Output: ["this is hot dog", "this is hotdog"]
*/
class MakeSentences {
    private final TrieNode dictionary;

    public MakeSentences(TrieNode dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> makeSentences(String word) {
        if (word == null) {
            return null;
        }

        List<String> results = new ArrayList<>();
        TrieNode current = dictionary;
        List<String> words = new ArrayList<>();
        for (char c : word.toCharArray()) {
            current = requireNonNull(current).children.get(c);
            if (current != null && current.isWord) {
                words.add(current.word);
                current = dictionary;
            }
        }

        results.add(join(" ", words));

        return results;
    }

    static class TrieNode {
        final Map<Character, TrieNode> children = new ConcurrentSkipListMap<>();
        String word;
        boolean isWord;

        public void insert(String word) {
            TrieNode current = this;
            for (char c : word.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
            }
            current.isWord = true;
            current.word = word;
        }

    }

}