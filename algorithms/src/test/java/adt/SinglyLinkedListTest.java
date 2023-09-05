package adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {
    @Test
    void insertAtEnd() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertNull(list.head);
        list.insert(10);
        assertNotNull(list.head);
        assertEquals(10, list.head.value);
        assertEquals(1, list.size);

        list.insert(20);
        assertEquals(20, list.head.next.value);
        assertEquals(2, list.size);

        list.insert(30);
        assertEquals(30, list.head.next.next.value);
        assertEquals(3, list.size);
    }

    @Test
    void insertAtPosition() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10, 0);
        list.insert(20);
        list.insert(30);
        list.insert(25, 2);
        assertEquals(25, list.head.next.next.value);

        list.insert(5, 0);
        assertEquals(5, list.head.value);
    }

    @Test
    void get() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));
        assertEquals(4, list.size);
    }

    @Test
    void contains() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
        assertTrue(list.contains(40));
        assertFalse(list.contains(50));
    }

    @Test
    void indexOf() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        assertEquals(0, list.indexOf(10));
        assertEquals(1, list.indexOf(20));
        assertEquals(2, list.indexOf(30));
        assertEquals(3, list.indexOf(40));
        assertEquals(-1, list.indexOf(50));
    }

    @Test
    void sizeAndEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(25, 2);
        list.insert(5, 0);
        list.insert(35, 5);

        assertEquals(6, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void insertPastEnd() {
        assertThrows(IllegalArgumentException.class, () -> new SinglyLinkedList<>().insert(666, 6));
    }

    @Test
    void remove() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        assertEquals(3, list.size);

        Integer value = list.remove(0);
        assertEquals(10, value);
        assertEquals(20, list.head.value);
        assertEquals(2, list.size);

        value = list.remove(0);
        assertEquals(20, value);
        assertEquals(30, list.head.value);
        assertEquals(1, list.size);

        list.insert(40);
        assertEquals(2, list.size);

        value = list.remove(1);
        assertEquals(40, value);
        assertEquals(1, list.size);
    }

    @Test
    void iterator() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertThrows(NoSuchElementException.class, () -> list.iterator().next());

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        final Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(20, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(30, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(40, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}