package adt;

import java.util.Map;

public interface Cache<K, V> extends Map<K, V> {
    int capacity();
}
