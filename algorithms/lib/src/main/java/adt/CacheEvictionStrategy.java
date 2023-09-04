package adt;

public interface CacheEvictionStrategy<E> {
    E evict();
}
