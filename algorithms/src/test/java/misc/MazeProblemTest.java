package misc;

import org.junit.jupiter.api.Test;

import static misc.MazeProblem.paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MazeProblemTest {
    @Test
    void baseCase() {
        assertEquals(1L, paths(1, 1));
        assertEquals(1L, paths(1, 1));
        assertEquals(1L, paths(1, 10));
        assertEquals(1L, paths(10, 1));
    }

    @Test
    void testNonTrivial() {
        assertEquals(26334L, paths(18, 6));
        assertEquals(5873182941643167150L, paths(75, 19));
    }
}