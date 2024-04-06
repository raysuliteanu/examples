package cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class PluggableStrategyCache<K, V> implements Cache<K, V> {
    private CacheEvictionStrategy<CacheEntry<K, V>> evictionStrategy;
    private final int capacity;
    private final Map<K, V> cache;

    public PluggableStrategyCache(final CacheEvictionStrategy<CacheEntry<K, V>> evictionStrategy, final int capacity, final Map<K, V> cache) {
        this.evictionStrategy = evictionStrategy;
        this.capacity = capacity;
        this.cache = cache;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return cache.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return cache.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return cache.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        return null;
    }

    @Override
    public V remove(final Object key) {
        return null;
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        cache.clear();
    }


    @Override
    public Set<K> keySet() {
        return Collections.emptySet();
    }

    @Override
    public Collection<V> values() {
        return Collections.emptyList();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
