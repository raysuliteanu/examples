package graph;

public class BinaryNode<T> {
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

    public T getValue() {
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
}
