package adt;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class DefaultTrie implements Trie {
    private final TrieNode root = new TrieNode();

    @Override
    public void insert(String word) {
        checkArgument(word);

        root.insert(word);
    }

    @Override
    public boolean find(String word) {
        if (word == null) {
            return false;
        }

        return root.find(word);
    }

    @Override
    public void delete(String word) {
        checkArgument(word);

        root.delete(word);
    }

    @Override
    public List<String> matches(final String word) {
        checkArgument(word);

        return root.matches(word);
    }

    private void checkArgument(final String word) {
        if (word == null) {
            throw new IllegalArgumentException("word parameter is null");
        }
    }

    private class TrieNode {
        String content;
        final Map<Character, TrieNode> children = new ConcurrentSkipListMap<>();
        boolean endOfWord;

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
            }
            current.endOfWord = true;
            current.content = word;
        }

        public boolean find(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                current = current.children.get(c);
                if (current == null) {
                    return false;
                }
            }

            return current.endOfWord;
        }

        public void delete(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                if (current.children.containsKey(c)) {
                    TrieNode child = current.children.get(c);
                    if (child.endOfWord && child.content.equals(word)) {
                        current.children.remove(c);
                    }

                    current = child;
                }
            }
        }

        public List<String> matches(final String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                }
            }

            List<TrieNode> wordNodes = bfsTrieNodesThatAreFullWords(current);

            return wordNodes.stream()
                    .filter(node -> node.endOfWord)
                    .map(node -> node.content)
                    .collect(Collectors.toList());
        }


        private List<TrieNode> bfsTrieNodesThatAreFullWords(final TrieNode start) {
            Queue<TrieNode> toVisitNodes = new LinkedList<>();
            toVisitNodes.add(start);

            List<TrieNode> wordNodes = new LinkedList<>();
            while (!toVisitNodes.isEmpty()) {
                TrieNode node = toVisitNodes.remove();
                if (node.endOfWord) {
                    wordNodes.add(node);
                }
                toVisitNodes.addAll(node.children.values());
            }
            return wordNodes;
        }
    }
}
