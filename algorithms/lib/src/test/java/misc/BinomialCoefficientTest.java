package misc;

import org.junit.jupiter.api.Test;

import static misc.BinomialCoefficient.binomialCoefficient;
import static misc.BinomialCoefficient.binomialCoefficientMemoized;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinomialCoefficientTest {
    @Test
    void binomialCoefficientUnoptimized() {
        assertEquals(21, binomialCoefficient(7, 2));
        assertEquals(6, binomialCoefficient(4, 2));
        assertEquals(120, binomialCoefficient(10, 3));
    }

    @Test
    void binomialCoefficientOptimized() {
        assertEquals(21, binomialCoefficientMemoized(7, 2));
        assertEquals(6, binomialCoefficientMemoized(4, 2));
        assertEquals(120, binomialCoefficientMemoized(10, 3));
    }
}