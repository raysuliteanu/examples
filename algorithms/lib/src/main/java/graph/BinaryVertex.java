package graph;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class BinaryVertex<T> extends AbstractValueVertex<T> {
    private BinaryVertex<T> left;
    private BinaryVertex<T> right;

    public BinaryVertex(final int number) {
        super(number);
    }

    public BinaryVertex(int number, T value) {
        super(number, value);
    }

    public BinaryVertex(int number, T value, BinaryVertex<T> left, BinaryVertex<T> right) {
        super(number, value);
        this.left = left;
        this.right = right;
    }

    @Override
    public List<Vertex<?>> children() {
        return unmodifiableList(asList(left, right));
    }

    public BinaryVertex<T> getLeft() {
        return left;
    }

    public void setLeft(final BinaryVertex<T> left) {
        this.left = left;
    }

    public BinaryVertex<T> getRight() {
        return right;
    }

    public void setRight(final BinaryVertex<T> right) {
        this.right = right;
    }
}
