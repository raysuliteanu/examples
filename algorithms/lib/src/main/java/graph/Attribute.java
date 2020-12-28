package graph;

public class Attribute<V> implements Comparable<Attribute<V>> {
    private final String name;
    private final V value;

    public Attribute(final String name, final V value) {
        this.name = name;
        this.value = value;
    }

    String name() {
        return name;
    }

    V value() {
        return value;
    }

    @Override
    public int compareTo(final Attribute<V> attribute) {
        return this.name.compareTo(attribute.name);
    }
}
