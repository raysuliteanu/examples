package misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

class LFUCache {
    private final int capacity;
    private final PriorityQueue<Entry> lfu;
    private final Map<Integer, Entry> cache;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        lfu = new PriorityQueue<>(capacity);
        cache = new HashMap<>(capacity);
    }

    public int get(int key) {
        synchronized (cache) {
            Entry entry = cache.get(key);
            if (entry == null) {
                return -1;
            }

            update(entry, entry.value);

            return entry.value;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        synchronized (cache) {
            if (cache.containsKey(key)) {
                update(key, value);
            }
            else {
                if (cache.size() == capacity) {
                    evict();
                }

                Entry entry = new Entry();
                entry.key = key;
                entry.value = value;
                entry.accessCount = 1;
                entry.lastAccessTimeNanos = System.nanoTime();
                cache.put(key, entry);
                lfu.add(entry);
            }
        }
    }

    void evict() {
        Entry entry = lfu.remove();
        cache.remove(entry.key);
    }

    void update(int key, int value) {
        final Entry entry = cache.get(key);
        update(entry, value);
    }

    void update(final Entry entry, final int value) {
        lfu.remove(entry);

        entry.value = value;
        ++entry.accessCount;
        entry.lastAccessTimeNanos = System.nanoTime();

        lfu.add(entry);
        cache.put(entry.key, entry);
    }

    private static class Entry implements Comparable<Entry> {
        int key;
        int value;
        int accessCount;
        long lastAccessTimeNanos;

        /** this compares first by access count which provides for LFU, but if two access counts are the same,
         * falls back to LRU, enabling a single priority queue to implement LFU + LRU.
         */
        public int compareTo(Entry e) {
            int compare = Integer.compare(accessCount, e.accessCount);
            return compare != 0 ? compare :
                    Long.compare(lastAccessTimeNanos, e.lastAccessTimeNanos);
        }

        public String toString() {
            return "Entry[key=" + key +
                    "; value=" + value +
                    "; accessCount=" + accessCount +
                    "; lastAccessTimeMs=" + lastAccessTimeNanos + "]";
        }

        public int hashCode() {
            return Objects.hash(key, value);
        }

        public boolean equals(Object e) {
            if (!(e instanceof Entry)) {
                return false;
            }

            Entry entry = (Entry) e;
            return key == entry.key && value == entry.value;
        }
    }
}
