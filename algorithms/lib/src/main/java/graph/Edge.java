package graph;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * An Edge connects exactly two {@link graph.Vertex}s.
 * An Edge can have arbitrary {@link Attribute}s e.g. an attribute could represent a boolean that the edge has been
 * visited or not, in some traversal algorithm.
 */
public class Edge {
    private final Vertex<?>[] vertices = new Vertex[2];
    private final Set<Attribute<?>> attributes = new TreeSet<>();

    public Edge(Vertex<?> first, Vertex<?> second) {
        assert first != null : "cannot add a null vertex";
        assert second != null : "cannot add a null vertex";
        vertices[0] = first;
        vertices[1] = second;
    }

    public Edge(Vertex<?> first, Vertex<?> second, Attribute<?>... attributes) {
        this(first, second);
        this.attributes.addAll(Arrays.asList(attributes.clone()));
    }

    public Vertex<?>[] vertices() {
        return vertices;
    }

    public void addAttribute(Attribute<?> attribute) {
        attributes.add(attribute);
    }

    public Optional<Attribute<?>> getAttribute(final String attributeName) {
        return attributes.stream().filter(value -> attributeName.equals(value.name())).findFirst();
    }
}
