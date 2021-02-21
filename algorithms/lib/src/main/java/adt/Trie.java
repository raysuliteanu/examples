package adt;

import java.util.List;

public interface Trie {
    void insert(String word);

    boolean find(String word);

    void delete(String word);

    List<String> matches(String word);
}
