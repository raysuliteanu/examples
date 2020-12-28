package graph;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractVertex<T> implements Vertex<T> {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private final int number = counter.getAndIncrement();

    @Override
    public int number() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final AbstractVertex<?> that = (AbstractVertex<?>) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
