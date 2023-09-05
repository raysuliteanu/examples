package graph;

import java.util.Objects;

public abstract class AbstractVertex<T> implements Vertex<T> {
    private final int number;

    public AbstractVertex(final int number) {
        this.number = number;
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractVertex<?> that = (AbstractVertex<?>) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number());
    }
}
