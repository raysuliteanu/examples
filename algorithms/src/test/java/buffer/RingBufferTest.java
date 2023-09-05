package buffer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {

    @Test
    void newBufferIsEmpty() {
        assertTrue(new RingBuffer(1).isEmpty());
    }

    @Test
    void newBufferIsNotFull() {
        assertFalse(new RingBuffer(1).isFull());
    }

    @Test
    void bufferWithDataNotEmpty() {
        RingBuffer ringBuffer = new RingBuffer(10);
        ringBuffer.insert(1);
        assertFalse(ringBuffer.isEmpty());
    }

    @Test
    void newBufferInsertThenRemoveIsEmpty() {
        RingBuffer ringBuffer = new RingBuffer(10);
        ringBuffer.insert(1);
        assertEquals(1, ringBuffer.remove());
        assertTrue(ringBuffer.isEmpty());
    }

    @Test
    void insertTillFull() {
        RingBuffer ringBuffer = new RingBuffer(10);
        for (int i = 0; i < 10; i++) {
            ringBuffer.insert(i);
        }
        assertTrue(ringBuffer.isFull());
    }

    @Test
    void peek() {
        RingBuffer ringBuffer = new RingBuffer(10);
        ringBuffer.insert(1);
        assertEquals(1, ringBuffer.peek());
        ringBuffer.insert(2);
        assertEquals(2, ringBuffer.peek());
    }

    @Test
    void size() {
        RingBuffer ringBuffer = new RingBuffer(10);
        for (int i = 0; i < 5; i++) {
            ringBuffer.insert(i);
        }
        assertEquals(5, ringBuffer.size());
    }

    @Test
    void capacity() {
        RingBuffer ringBuffer = new RingBuffer(10);
        assertEquals(10, ringBuffer.capacity());
    }
}