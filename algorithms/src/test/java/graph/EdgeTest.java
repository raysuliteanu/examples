package graph;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeTest {
    @Test
    void construction() {
        Edge edge = new Edge(new BinaryVertex<>(1), new BinaryVertex<>(2));
        Vertex<?>[] vertices = edge.vertices();
        assertEquals(1, vertices[0].number());
        assertEquals(2, vertices[1].number());
    }

    @Test
    void constructionWithAttributes() {
        Edge edge = new Edge(new BinaryVertex<>(1), new BinaryVertex<>(2),
                new Attribute<>("someAttribute", true), new Attribute<>("someOtherAttribute", 10));
        Vertex<?>[] vertices = edge.vertices();
        assertEquals(1, vertices[0].number());
        assertEquals(2, vertices[1].number());

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
        Edge edge = new Edge(new BinaryVertex<>(1), new BinaryVertex<>(2),
                new Attribute<>("someAttribute", true), new Attribute<>("someOtherAttribute", 10));

        edge.addAttribute(new Attribute<>("visited", true));

        Optional<Attribute<?>> someAttribute = edge.getAttribute("visited");
        assertTrue(someAttribute.isPresent());
        Attribute<?> attribute = someAttribute.get();
        assertTrue((boolean) attribute.value());
    }
}