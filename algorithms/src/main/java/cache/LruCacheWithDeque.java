package cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
// Add any extra import statements you may need here


class LruCacheWithDeque {

    // Add any helper functions you may need here
    private final Map<Integer, Integer> cache;
    private final Deque<Integer> priorityQueue;
    private final int capacity;

    public LruCacheWithDeque(int capacity) {
        // Write your code here
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        priorityQueue = new LinkedList<>();
    }

    public int get(int key) {
        // Write your code here
        int value = -1;

        synchronized (this) {
            if (cache.containsKey(key)) {
                value = cache.get(key);
                priorityQueue.remove(key);
                priorityQueue.addFirst(key);
            }
        }
        return value;
    }

    public int set(int key, int value) {
        // Write your code here
        synchronized (this) {
            if (cache.containsKey(key)) {
                priorityQueue.remove(key);
                priorityQueue.addFirst(key);
            }
            else {
                if (cache.size() == capacity) {
                    priorityQueue.removeLast();
                }
                cache.put(key, value);
                priorityQueue.addFirst(key);
            }
        }
        return value;
    }

}
