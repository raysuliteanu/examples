package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListMap;

import adt.DefaultTrie;

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

    public List<String> makeSentence(String word) {
        if (word == null) {
            return null;
        }

        List<String> results = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        Stack<String> possibles = new Stack<>();

        TrieNode cursor = dictionary;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            cursor = cursor.children.get(chars[i]);

            if (cursor != null) {
                if (cursor.isWord) {
                    if (i < chars.length - 1) {
                        builder.append(cursor.word);
                        possibles.push(builder.toString());
                        possibles.push(builder.append(" ").toString());
                    }
                    else {
                        for (String s : possibles) {
                            results.add(s + word);
                        }
                    }

                    cursor = dictionary;
                }
            }
            else {
                if (i < chars.length - 1) {
                    results.clear();
                }
                break;
            }

        }

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