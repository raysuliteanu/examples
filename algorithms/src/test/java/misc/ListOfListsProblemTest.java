package misc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static misc.ListOfListsProblem.solve;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListOfListsProblemTest {

    @Test
    void solveSimple() {
        final List<List<Integer>> solve = solve(new int[][]{{1,2}});
        assertEquals(List.of(List.of(1), List.of(2)), solve);
    }

    @Test
    void solveSimple2() {
        final List<List<Integer>> solve = solve(new int[][]{{1,2}, {3}});
        assertEquals(List.of(List.of(1, 3), List.of(2, 3)), solve);
    }

    @Test
    void solveSimple3() {
        final List<List<Integer>> solve = solve(new int[][]{{1,2}, {3}, {4}});
        assertEquals(List.of(List.of(1, 3, 4), List.of(2, 3, 4)), solve);
        System.out.println(solve);
    }
}