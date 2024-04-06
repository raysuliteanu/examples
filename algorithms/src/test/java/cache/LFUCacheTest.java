package cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LFUCacheTest {
    /*
    ["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
    [[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]

    [null,null,null,2,1,2,null,null,-1,2,1,4]
     */
    @Test
    void setOne() {
        LFUCache cache = new LFUCache(3);

        cache.put(2, 2);
        cache.put(1, 1);

        assertEquals(2, cache.get(2).get());
        assertEquals(1, cache.get(1).get());
        assertEquals(2, cache.get(2).get());

        cache.put(3, 3);
        cache.put(4, 4);

        assertTrue(cache.get(3).isEmpty());
        assertEquals(2, cache.get(2).get());
        assertEquals(1, cache.get(1).get());
        assertEquals(4, cache.get(4).get());
    }

    /*
    ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]

[null,null,null,1,null,-1,3,null,-1,3,4]

     */
    @Test
    void setTwo() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1).get());

        cache.put(3, 3);

        assertTrue(cache.get(2).isEmpty());
        assertEquals(3, cache.get(3).get());

        cache.put(4, 4);

        assertTrue(cache.get(1).isEmpty());
        assertEquals(3, cache.get(3).get());
        assertEquals(4, cache.get(4).get());
    }

    /*
    ["LFUCache","put","put","put","put","get"]
[[2],[3,1],[2,1],[2,2],[4,4],[2]]
     */
    @Test
    void setThree() {
        LFUCache cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);

        assertEquals(2, cache.get(2).get());
    }

    /*
    ["LFUCache","get","put","get","put","put","get","get"]
[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]

[null,-1,null,-1,null,null,2,6]
     */
    @Test
    void setFour() {
        LFUCache cache = new LFUCache(2);
        assertThat(cache.get(2)).isEmpty();
        assertThat(cache.get(2)).isEmpty();

        cache.put(2, 6);
        assertThat(cache.get(1)).isEmpty();

        cache.put(1, 5);
        cache.put(1, 2);

        assertThat(cache.get(1).get()).isEqualTo(2);
        assertThat(cache.get(2).get()).isEqualTo(6);
    }
}