package graph;

import java.util.List;

public interface Vertex<T> {
    int number();

    List<Vertex<?>> children();
}
