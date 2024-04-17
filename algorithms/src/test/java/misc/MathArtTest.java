package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathArtTest {
    @Test
    void testMethod() {
        MathArt mathArt = new MathArt();
        long totalPlusSigns = mathArt.getPlusSignCount(9, new int[]{6, 3, 4, 5, 1, 6, 3, 3, 4}, "ULDRULURD");
        assertEquals(4, totalPlusSigns);
    }
}