package misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {
    @Test
    public void removeValInTheMiddle() {
        LinkedListExample linkedList = new LinkedListExample();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }

        LinkedListExample.Node list = linkedList.removeAll(3);
        assertNotNull(list);
        assertFalse(linkedList.contains(3));
    }

    @Test
    public void removeAllSame() {
        LinkedListExample linkedList = new LinkedListExample();
        for (int i = 0; i < 10; i++) {
            linkedList.add(0);
        }

        LinkedListExample.Node list = linkedList.removeAll(0);
        assertNull(list);
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void removeLeading() {
        LinkedListExample linkedList = new LinkedListExample();
        for (int i = 0; i < 10; i++) {
            linkedList.add(0);
        }

        linkedList.add(1);

        LinkedListExample.Node list = linkedList.removeAll(0);
        assertNotNull(list);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(1));
    }

    @Test
    public void removeTrailing() {
        LinkedListExample linkedList = new LinkedListExample();

        linkedList.add(1);

        for (int i = 0; i < 10; i++) {
            linkedList.add(0);
        }

        final LinkedListExample.Node list = linkedList.removeAll(0);
        assertNotNull(list);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(1));
    }

    @Test
    public void removeLargeCombo() {
        LinkedListExample linkedList = new LinkedListExample();

        for (int j = 0; j < 1_000_000; j++) {
            for (int i = 0; i < 10; i++) {
                linkedList.add(0);
            }
            for (int i = 0; i < 10; i++) {
                linkedList.add(1);
            }
        }

        final LinkedListExample.Node list = linkedList.removeAll(0);
        assertNotNull(list);
        assertFalse(linkedList.isEmpty());
        assertFalse(linkedList.contains(0));
    }

    @Test
    public void iterator() {
        LinkedListExample linkedList = new LinkedListExample();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }

        List<Integer> results = new ArrayList<>(10);
        final Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            results.add(iterator.next());
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(results.contains(i));
        }
    }
}