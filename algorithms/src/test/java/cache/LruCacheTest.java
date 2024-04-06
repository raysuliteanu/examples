package cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LruCacheTest {

    @Test
    void evictLruEntry() {
        final LruCache lruCache = new LruCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        assertNull(lruCache.get(1));
        assertEquals(2, lruCache.get(2));
        assertEquals(3, lruCache.get(3));
        assertEquals(4, lruCache.get(4));

        lruCache.put(5, 5);
        assertNull(lruCache.get(2));
    }
}