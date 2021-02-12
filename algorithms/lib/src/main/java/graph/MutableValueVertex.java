package graph;

public interface MutableValueVertex<T> extends ValueVertex<T> {
    void setValue(T value);
}
