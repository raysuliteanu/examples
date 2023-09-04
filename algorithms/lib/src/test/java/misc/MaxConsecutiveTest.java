package misc;

import org.junit.jupiter.api.Test;

import static misc.MaxConsecutive.maxConsecutiveInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveTest {

    @Test
    void empty() {
        assertEquals(0, maxConsecutiveInteger(new int[]{}));
    }

    @Test
    void oneElement() {
        assertEquals(1, maxConsecutiveInteger(new int[]{1}));
    }

    @Test
    void manyElements() {
        assertEquals(1, maxConsecutiveInteger(new int[]{5, 3, 5}));
        assertEquals(3, maxConsecutiveInteger(new int[]{4, 2, 1, 6, 5}));
        assertEquals(3, maxConsecutiveInteger(new int[]{11, -2, 8, -1, 4, 0, 3}));
    }
}