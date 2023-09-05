package graph;

public abstract class AbstractValueVertex<T> extends AbstractVertex<T> implements ValueVertex<T> {
    protected T value;

    public AbstractValueVertex(final int number) {
        this(number, null);
    }

    public AbstractValueVertex(final Integer number, final T value) {
        super(number);
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }
}
