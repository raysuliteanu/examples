package buffer;

/**
 * Fixed-length circular buffer
 */
public class RingBuffer {
    private final int[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public RingBuffer(int capacity) {
        buffer = new int[capacity];
    }

    void insert(final int value) {
        if (isFull()) {
            throw new RuntimeException("buffer is full");// todo: throw better exception
        }
        buffer[head] = value;
        head = (head + 1) % buffer.length;
        ++count;
    }

    int peek() {
        if (isEmpty()) {
            throw new RuntimeException("peeking on an empty buffer"); // todo: throw better exception
        }
        return buffer[head - 1];
    }

    int remove() {
        if (isEmpty()) {
            throw new RuntimeException("remove on an empty buffer");// todo: throw better exception
        }

        int value = buffer[tail];
        tail = (tail + 1) % buffer.length;
        --count;
        return value;
    }

    boolean isFull() {
        return count == buffer.length;
    }

    boolean isEmpty() {
        return count == 0;
    }

    int size() {
        return count;
    }

    int capacity() {
        return buffer.length;
    }
}
