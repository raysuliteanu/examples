package misc;

import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Comparator.comparingInt;

/*
Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and
ending at (m - 1, n - 1) moving in the 4 cardinal directions.

The score of a path is the minimum value in that path.
 */
public class PathWithMaxMinValue {
    public int maximumMinimumPath(int[][] grid) {
        PriorityQueue<MatrixPath> scores = new PriorityQueue<>(comparingInt(o -> -o.score));
        findPath(grid, scores);
        return scores.peek().score;
    }

    private void findPath(final int[][] grid, final PriorityQueue<MatrixPath> scores) {

    }

    static class MatrixPath {
        int score;
        Queue<Cell> path;

        void insert(int x, int y, int val) {
            path.add(new Cell(new CellCoordinate(x, y), val));
            if (val < score) {
                score = val;
            }
        }
    }

    static class Cell {
        CellCoordinate pos;
        int value;

        public Cell(final CellCoordinate pos, final int value) {
            this.pos = pos;
            this.value = value;
        }
    }

    static class CellCoordinate {
        int x;
        int y;

        public CellCoordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

}
