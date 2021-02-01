package graph;

class SimpleVertex extends AbstractVertex<String> {
    private final String value;

    public SimpleVertex(final Integer vertex) {
        this(vertex, String.valueOf(vertex));
    }

    public SimpleVertex(final Integer vertex, final String value) {
        super(vertex);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return number() + (value.length() > 0 ? " (" + value + ")" : "");
    }
}
