package graph;

import java.util.Collections;
import java.util.List;

public interface Vertex<T> {
    int number();

    default List<Vertex<?>> children() {
        return Collections.emptyList();
    }

    ;
}
