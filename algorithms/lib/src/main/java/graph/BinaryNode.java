package graph;

public class BinaryNode<T> extends AbstractVertex<T> {
    private final T value;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T value) {
        this.value = value;
    }

    public BinaryNode(int number, T value) {
        super(number);
        this.value = value;
    }

    public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(int number, T value, BinaryNode<T> left, BinaryNode<T> right) {
        super(number);
        this.value = value;
        this.left = left;
        this.right = right;
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
}
