package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathArtTest {
    MathArt mathArt = new MathArt();

    @Test
    void testNineLines() {
        long totalPlusSigns = mathArt.getPlusSignCount(9, new int[]{6, 3, 4, 5, 1, 6, 3, 3, 4}, "ULDRULURD");
        assertEquals(4, totalPlusSigns);
    }

    @Test
    void testEightLinesAllLengthOne() {
        long totalPlusSigns = mathArt.getPlusSignCount(8, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, "RDLUULDR");
        assertEquals(1, totalPlusSigns);
    }

    @Test
    void testAnotherEight() {
        long totalPlusSigns = mathArt.getPlusSignCount(8, new int[]{1, 2, 2, 1, 1, 2, 2, 1}, "UDUDLRLR");
        assertEquals(1, totalPlusSigns);
    }
}