package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquilibriumSumTest {
    private final EquilibriumSum equilibriumSum = new EquilibriumSum();

    @Test
    void simple() {
        assertEquals(1, equilibriumSum.equilibriumIndex(new int[]{-1, 3, -4, 5, 1, -6, 2, 1}));
        assertEquals(1, equilibriumSum.equilibriumIndex(new int[]{-1, 3, -1}));
        assertEquals(-1, equilibriumSum.equilibriumIndex(new int[]{-1}));
        assertEquals(-1, equilibriumSum.equilibriumIndex(new int[]{-1, 1}));
        assertEquals(-1, equilibriumSum.equilibriumIndex(new int[]{}));
    }

    @Test
    void overflow() {
        assertEquals(-1, equilibriumSum.equilibriumIndex(new int[]{Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE}));
    }

    @Test
    void underflow() {
        assertEquals(-1, equilibriumSum.equilibriumIndex(new int[]{Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 3, Integer.MIN_VALUE}));
    }
}