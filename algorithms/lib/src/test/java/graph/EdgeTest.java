package graph;

import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeTest {
    @Test
    void construction() {
        Edge edge = new Edge(new BinaryNode<>(1), new BinaryNode<>(2));
        Vertex<?>[] vertices = edge.vertices();
        assertEquals(1, vertices[0].value());
        assertEquals(2, vertices[1].value());
    }

    @Test
    void constructionWithAttributes() {
        Edge edge = new Edge(new BinaryNode<>(1), new BinaryNode<>(2),
                new Attribute<>("someAttribute", true), new Attribute<>("someOtherAttribute", 10));
        Vertex<?>[] vertices = edge.vertices();
        assertEquals(1, vertices[0].value());
        assertEquals(2, vertices[1].value());

        Optional<Attribute<?>> someAttribute = edge.getAttribute("someAttribute");
        assertTrue(someAttribute.isPresent());
        Attribute<?> attribute = someAttribute.get();
        assertTrue((boolean) attribute.value());

        someAttribute = edge.getAttribute("someOtherAttribute");
        assertTrue(someAttribute.isPresent());
        attribute = someAttribute.get();
        assertEquals(10, attribute.value());
    }

    @Test
    void addAttribute() {
        Edge edge = new Edge(new BinaryNode<>(1), new BinaryNode<>(2),
                new Attribute<>("someAttribute", true), new Attribute<>("someOtherAttribute", 10));

        edge.addAttribute(new Attribute<>("visited", true));

        Optional<Attribute<?>> someAttribute = edge.getAttribute("visited");
        assertTrue(someAttribute.isPresent());
        Attribute<?> attribute = someAttribute.get();
        assertTrue((boolean) attribute.value());
    }
}