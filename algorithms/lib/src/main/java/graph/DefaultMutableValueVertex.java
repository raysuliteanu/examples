package graph;

public class DefaultMutableValueVertex<T> extends AbstractValueVertex<T> implements MutableValueVertex<T> {
    public DefaultMutableValueVertex(final int number) {
        super(number);
    }

    public DefaultMutableValueVertex(final Integer number, final T value) {
        super(number, value);
    }

    @Override
    public void setValue(final T value) {
        this.value = value;
    }
}
