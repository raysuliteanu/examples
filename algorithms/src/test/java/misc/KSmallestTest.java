package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KSmallestTest {
    KSmallest kSmallest = new KSmallest();

    @Test
    void setOne() {
        assertEquals(10, kSmallest.findKthNumber(13, 2));
        assertEquals(2, kSmallest.findKthNumber(10, 3));
    }

    @Test
    void setTwo() {
        assertEquals(10, kSmallest.findKthNumber(4_289_384, 1_922_239));
    }
}