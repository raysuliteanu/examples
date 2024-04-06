package cache;

public interface CacheEvictionStrategy<E> {
    E evict();
}
