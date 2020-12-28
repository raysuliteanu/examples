package graph;

import java.util.Objects;

class SimpleVertex implements Vertex<String> {
    private final Integer vertex;
    private final String value;

    public SimpleVertex(final Integer vertex, final String value) {
        assert vertex != null : "vertex number is required";
        this.vertex = vertex;
        this.value = value;
    }

    @Override
    public int number() {
        return vertex;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final SimpleVertex that = (SimpleVertex) o;
        return vertex.equals(that.vertex) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, value);
    }
}
