package misc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameScoringTest {
    GameScoring gameScoring = new GameScoring();

    @Test
    void input1() {
        int[][] output = gameScoring.gameScoring(1);
        int[][] expectedOutput = new int[][]{
                {1}
        };
        assertTrue(Arrays.deepEquals(output, expectedOutput));
    }

    @Test
    void input2() {
        int[][] output = gameScoring.gameScoring(2);
        int[][] expectedOutput = new int[][]{
                {1, 1},
                {2}
        };
        assertTrue(Arrays.deepEquals(output, expectedOutput));
    }

    @Test
    void input3() {
        int[][] output = gameScoring.gameScoring(3);
        int[][] expectedOutput = new int[][]{
                {1, 1, 1},
                {1, 2},
                {2, 1},
                {3}
        };
        assertTrue(Arrays.deepEquals(output, expectedOutput));
    }

    @Test
    void input4() {
        int[][] output = gameScoring.gameScoring(4);
        int[][] expectedOutput = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 2},
                {1, 2, 1},
                {1, 3},
                {2, 1, 1},
                {2, 2},
                {3, 1}
        };
        assertTrue(Arrays.deepEquals(output, expectedOutput));
    }

    @Test
    void input5() {
        int[][] output = gameScoring.gameScoring(5);
        int[][] expectedOutput = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 2},
                {1, 1, 2, 1},
                {1, 1, 3},
                {1, 2, 1, 1},
                {1, 2, 2},
                {1, 3, 1},
                {2, 1, 1, 1},
                {2, 1, 2},
                {2, 2, 1},
                {2, 3},
                {3, 1, 1},
                {3, 2}
        };
        assertTrue(Arrays.deepEquals(output, expectedOutput));
    }
}