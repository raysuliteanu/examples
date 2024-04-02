package misc;

import java.util.Iterator;

public class LinkedListExample {
    static class Node {
        int val;
        Node next;
    }

    private Node head;
    private Node tail;
    private int size;

    Node removeAll(int val) {
        if (head != null) {
            removeLeading(val);

            if (head != null) {
                Node current = head;
                Node cursor = head.next;
                while (cursor != null) {
                    cursor = removeConsecutive(val, current, cursor);
                    current = cursor;
                    cursor = cursor != null ? cursor.next : null;
                }
            }
        }

        return head;
    }

    private void removeLeading(final int val) {
        while (head != null && head.val == val) {
            head = head.next;
            --size;
        }
    }

    private Node removeConsecutive(final int val, final Node start, Node cursor) {
        while (cursor != null && cursor.val == val) {
            start.next = cursor.next;
            cursor = cursor.next;
            --size;
        }
        return cursor;
    }

    public void add(int val) {
        if (head == null) {
            head = new Node();
            head.val = val;
            tail = head;
        }
        else {
            Node newNode = new Node();
            newNode.val = val;
            tail.next = newNode;
            tail = newNode;
        }
        ++size;
    }

    public boolean contains(int val) {
        Node cursor = head;
        while (cursor != null) {
            if (cursor.val == val) {
                return true;
            }

            cursor = cursor.next;
        }

        return false;
    }

    public void clear() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node cursor = head;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public Integer next() {
                int val = cursor.val;
                cursor = cursor.next;
                return val;
            }
        };
    }
}
