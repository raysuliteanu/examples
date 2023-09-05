package adt;

import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {
    private final int[] data;
    private final Semaphore semaphore;
    private int head;
    private int tail;
    private int size;

    public BoundedBlockingQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be > 0");
        }

        data = new int[capacity];
        semaphore = new Semaphore(capacity);
    }

    public void enqueue(int element) throws InterruptedException {
        semaphore.acquire();

        synchronized (semaphore) {
            data[head] = element;
            head = (head + 1) % data.length;
            ++size;
        }

        synchronized (this) {
            notify();
        }
    }

    public int dequeue() throws InterruptedException {

        synchronized (this) {
            while (isEmpty()) {
                wait(1);
            }
        }

        int value;

        synchronized (semaphore) {
            value = data[tail];
            tail = (tail + 1) % data.length;
            --size;
        }

        semaphore.release();

        return value;
    }

    public int size() {
        synchronized (semaphore) {
            return size;
        }
    }

    boolean isEmpty() {
        synchronized (semaphore) {
            return size == 0;
        }
    }
}

