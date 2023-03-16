package graph;

import java.util.Collections;
import java.util.List;

public interface Vertex<T> {
    int number();

    default List<Vertex<?>> children() {
        return Collections.emptyList();
    }

    static <T> Vertex<T> of(int index) {
        return new GenericVertex<>(index);
    }
}
