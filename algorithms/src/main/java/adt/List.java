package adt;

import java.util.Iterator;

public interface List<E> {
    /**
     * insert element at the end of the list
     */
    void insert(E element);

    /**
     * insert element at the specified 0-based location
     */
    void insert(E element, int location);

    /**
     * remove the element from the 0-based location and return it
     */
    E remove(int location);

    /**
     * retrieve the element from the 0-based location
     */
    E get(int location);

    /**
     * provide an iterator over the contents of the list
     */
    Iterator<E> iterator();

    /**
     * returns true if the element exists in the list, false otherwise
     */
    boolean contains(E value);

    /**
     * return the 0-based index of the node matching value, or -1 if the value is not in the list
     */
    int indexOf(E value);

    int size();

    boolean isEmpty();
}

