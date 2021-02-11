package adt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class BoundedBlockingQueueTest {
    @Test
    void capacity() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedBlockingQueue(0));
        assertThrows(IllegalArgumentException.class, () -> new BoundedBlockingQueue(-1));
    }

    @Test
    void newBufferIsEmpty() {
        assertTrue(new BoundedBlockingQueue(1).isEmpty());
    }

    @Test
    void bufferWithDataNotEmpty() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(10);
        boundedBlockingQueue.enqueue(1);
        assertFalse(boundedBlockingQueue.isEmpty());
    }

    @Test
    void newBufferInsertThenRemoveIsEmpty() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(10);
        boundedBlockingQueue.enqueue(1);
        assertEquals(1, boundedBlockingQueue.dequeue());
        assertTrue(boundedBlockingQueue.isEmpty());
    }

    @Test
    void insertTillFull() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(10);
        for (int i = 0; i < 10; i++) {
            boundedBlockingQueue.enqueue(i);
        }
        assertEquals(10, boundedBlockingQueue.size());
    }

    @Test
    void size() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(10);
        for (int i = 0; i < 5; i++) {
            boundedBlockingQueue.enqueue(i);
        }
        assertEquals(5, boundedBlockingQueue.size());
    }

    @Test
    void dequeueBlocksWhenEmpty() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(10);

        Thread consumer = new Thread(() -> {
            try {
                int value = boundedBlockingQueue.dequeue();
                assertEquals(666, value);
            }
            catch (InterruptedException e) {
                fail(e);
            }
        });

        Thread producer = new Thread(() -> {
            try {
                boundedBlockingQueue.enqueue(666);
            }
            catch (InterruptedException e) {
                fail(e);
            }
        });

        consumer.start();
        producer.start();

        consumer.join();
        producer.join();
    }

    @Test
    void multithreadedSequence() throws InterruptedException {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(2);

        Thread consumer = new Thread(() -> {
            try {
                assertEquals(1, boundedBlockingQueue.dequeue());
                assertEquals(0, boundedBlockingQueue.dequeue());
                assertEquals(2, boundedBlockingQueue.dequeue());
            }
            catch (InterruptedException e) {
                fail(e);
            }
        }, "consumer");

        Thread producer = new Thread(() -> {
            try {
                boundedBlockingQueue.enqueue(1);
                boundedBlockingQueue.enqueue(0);
                boundedBlockingQueue.enqueue(2);
                boundedBlockingQueue.enqueue(3);
                boundedBlockingQueue.enqueue(4);
            }
            catch (InterruptedException e) {
                fail(e);
            }
        }, "producer");

        consumer.start();
        producer.start();

        consumer.join();
        producer.join();

        assertEquals(2, boundedBlockingQueue.size());
    }
}