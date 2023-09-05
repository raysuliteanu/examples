package misc;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class LruCache implements Map<Integer, Integer> {
    private final int capacity;
    private final Map<Integer, Entry> cache;
    private final PriorityQueue<Entry> lru;

    public LruCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        lru = new PriorityQueue<>(capacity);
    }

    @Override
    public synchronized Integer get(final Object key) {
        Entry entry = cache.get(key);
        if (entry == null) {
            return null;
        }

        update(entry, entry.value);

        return entry.value;
    }

    public synchronized Integer put(Integer key, Integer value) {
        Integer oldValue = null;
        if (capacity != 0) {
            Entry entry = cache.get(key);
            if (entry != null) {
                oldValue = entry.value;
                update(entry, value);
            } else {
                if (cache.size() == capacity) {
                    evict();
                }

                entry = Entry.of(key, value);
                cache.put(key, entry);
                lru.add(entry);
            }
        }

        return oldValue;
    }

    @Override
    public synchronized Integer remove(final Object key) {
        final Entry removed = cache.remove(key);
        if (removed != null) {
            lru.remove(removed);
            return removed.value;
        }

        return null;
    }

    @Override
    public synchronized void putAll(final Map<? extends Integer, ? extends Integer> m) {
        for (final Map.Entry<? extends Integer, ? extends Integer> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public synchronized boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public synchronized boolean containsKey(final Object key) {
        return cache.containsKey(key);
    }

    @Override
    public synchronized boolean containsValue(final Object value) {
        return cache.containsValue(value);
    }

    @Override
    public synchronized void clear() {
        cache.clear();
        lru.clear();
    }

    @Override
    public synchronized Set<Integer> keySet() {
        return Collections.unmodifiableSet(cache.keySet());
    }

    @Override
    public synchronized Collection<Integer> values() {
        return cache.values().stream()
                .map(e -> e.value)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Set<Map.Entry<Integer, Integer>> entrySet() {
        return cache.entrySet().stream()
                .map(e -> new Map.Entry<Integer, Integer>() {
                    @Override
                    public Integer getKey() {
                        return e.getKey();
                    }

                    @Override
                    public Integer getValue() {
                        return e.getValue().value;
                    }

                    @Override
                    public Integer setValue(final Integer value) {
                        throw new UnsupportedOperationException();
                    }
                })
                .collect(Collectors.toUnmodifiableSet());
    }

    public int capacity() {
        return capacity;
    }

    public synchronized int size() {
        return cache.size();
    }

    private void evict() {
        final Entry entry = lru.poll();
        assert entry != null : "should never call evict() on empty cache";
        cache.remove(entry.key);
    }

    private void update(final Entry entry, final int value) {
        lru.remove(entry);
        final Entry newEntry = Entry.of(entry.key, value);
        cache.put(entry.key, newEntry);
        lru.add(newEntry);
    }

    private static class Entry implements Comparable<Entry> {
        final int key;
        final int value;
        final long lastAccessTimeNanos;

        private Entry(final int key, final int value, final long lastAccessTimeNanos) {
            this.key = key;
            this.value = value;
            this.lastAccessTimeNanos = lastAccessTimeNanos;
        }

        static Entry of(final int key, final int value) {
            return new Entry(key, value, System.nanoTime());
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Entry entry = (Entry) o;
            return key == entry.key && value == entry.value && lastAccessTimeNanos == entry.lastAccessTimeNanos;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, lastAccessTimeNanos);
        }

        @Override
        public int compareTo(final Entry o) {
            return Long.compare(lastAccessTimeNanos, o.lastAccessTimeNanos);
        }
    }
}
