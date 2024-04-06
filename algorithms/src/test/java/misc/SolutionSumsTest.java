package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionSumsTest {
    SolutionSums solutionSums = new SolutionSums();

    @Test
    void countPairs() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        assertEquals(0, solutionSums.countPairs(array, 9));
        assertEquals(3, solutionSums.countPairs(array, 8));
        assertEquals(2, solutionSums.countPairs(array, 5));
    }
}