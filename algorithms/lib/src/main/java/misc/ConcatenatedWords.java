package misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = insert(words);

        List<String> results = new LinkedList<>();
        for (String word : words) {
            int countWords = 0;
            boolean valid = true;
            Trie current = root;
            char[] chars = word.toCharArray();
            for (final char c : chars) {
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                    if (current.isWord && word.contains(current.word)) {
                        ++countWords;
                        System.out.println("counting " + current.word);
                    }
                }
                else {
                    valid = false;
                    current = root;
                }
            }

            if (valid && countWords > 1) {
                System.out.println("adding " + word);
                results.add(word);
            }
        }

        return results;
    }

    static class Trie {
        Map<Character, Trie> children = new HashMap<>();
        String word;
        boolean isWord;
    }

    Trie insert(String[] words) {
        Trie root = new Trie();
        Trie current = root;
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                Trie temp = current.children.get(c);
                if (temp == null) {
                    temp = new Trie();
                    current.children.put(c, temp);
                }

                current = temp;
            }
            current.isWord = true;
            current.word = word;
            current = root;
        }

        return root;
    }
}
