package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinSetSizeTest {
    MinSetSize minSetSize = new MinSetSize();

    @Test
    void test() {
        assertEquals(2, minSetSize.minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
        assertEquals(1, minSetSize.minSetSize(new int[]{7, 7, 7, 7, 7, 7}));
        assertEquals(1, minSetSize.minSetSize(new int[]{1, 9}));
        assertEquals(1, minSetSize.minSetSize(new int[]{1000, 1000, 3, 7}));
        assertEquals(5, minSetSize.minSetSize(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}