package adt;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    SingleLinkNode<E> head;
    int size;

    @Override
    public void insert(final E element) {
        SingleLinkNode<E> node = new SingleLinkNode<>();
        node.value = element;

        if (head == null) {
            head = node;
        } else {
            SingleLinkNode<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }

        ++size;
    }

    @Override
    public void insert(final E element, final int location) {
        if (location > size) {
            throw new IllegalArgumentException("location " + location + " greater than list size " + size);
        }

        SingleLinkNode<E> node = new SingleLinkNode<>();
        node.value = element;

        if (head == null) {
            head = node;
        } else {
            int index = 1;
            SingleLinkNode<E> current = head;
            while (index++ < location) {
                current = current.next;
            }

            if (current == head) {
                head = node;
            } else {
                current.next = node;
            }

            node.next = current.next;
        }

        ++size;
    }

    @Override
    public E remove(final int location) {
        if (isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }

        if (location > size) {
            throw new IllegalArgumentException("location " + location + " greater than list size " + size);
        }


        int index = 0;
        SingleLinkNode<E> current = null;
        SingleLinkNode<E> next = head;
        while (index++ < location) {
            current = next;
            next = next.next;
        }

        SingleLinkNode<E> removed;

        if (current == null) {
            removed = head;
            head = next.next;
        } else {
            removed = next;
            current.next = next.next;
        }

        removed.next = null;

        --size;

        return removed.value;
    }

    @Override
    public E get(final int location) {
        SingleLinkNode<E> current = head;

        int index = 0;
        while (index++ < location) {
            current = current.next;
        }

        return current.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(head, size);
    }

    @Override
    public boolean contains(final E value) {
        SingleLinkNode<E> current = head;

        int index = 0;
        while (index++ < size) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public int indexOf(final E value) {
        SingleLinkNode<E> current = head;

        int index = 0;
        while (index < size) {
            if (current.value.equals(value)) {
                return index;
            }

            current = current.next;
            ++index;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    static class SingleLinkNode<E> extends AbstractLinkNode<E> {
        SingleLinkNode<E> next;
    }

    private class ListIterator<T> implements Iterator<T> {
        final int originalSize;
        int index = 0;
        SingleLinkNode<T> currentNode;

        public ListIterator(final SingleLinkNode<T> head, final int size) {
            this.currentNode = head;
            originalSize = size;
        }

        @Override
        public boolean hasNext() {
            if (originalSize != size) {
                throw new ConcurrentModificationException();
            }

            return index < originalSize;
        }

        @Override
        public T next() {
            if (originalSize != size) {
                throw new ConcurrentModificationException();
            }

            if (currentNode == null) {
                throw new NoSuchElementException();
            }

            T value = currentNode.value;
            currentNode = currentNode.next;
            ++index;

            return value;
        }
    }
}
