package graph;

import java.util.Objects;

public class BinaryNode<T> extends AbstractVertex<T> {
    private final T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(final BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(final BinaryNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }
        final BinaryNode<?> that = (BinaryNode<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
