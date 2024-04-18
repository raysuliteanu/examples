package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlantsTest {
    final Plants plants = new Plants();

    @Test
    void testOne() {
        int[] input = {6, 5, 8, 4, 7, 10, 9};
        assertEquals(2, plants.poisonousPlants(input));
    }

    @Test
    void testTwo() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(1, plants.poisonousPlants(input));
    }

    @Test
    void testThree() {
        int[] input = {1};
        assertEquals(0, plants.poisonousPlants(input));
    }

    @Test
    void testFour() {
        int[] input = {1, 1};
        assertEquals(0, plants.poisonousPlants(input));
    }

    @Test
    void testFive() {
        int[] input = {1, 1, 2};
        assertEquals(1, plants.poisonousPlants(input));
    }
}