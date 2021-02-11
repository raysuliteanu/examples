package graph;

import java.util.List;

class SimpleVertex extends AbstractVertex<String> {
    public SimpleVertex(final int number) {
        super(number);
    }

    @Override
    public List<Vertex<?>> children() {
        return null;
    }
}
