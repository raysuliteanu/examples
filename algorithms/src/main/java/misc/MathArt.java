package misc;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * (from <a href="https://www.metacareers.com/profile/coding_puzzles?puzzle=587690079288608">here</a>)
 * <p>
 * You are creating a special painting on a canvas which may be represented as a 2D Cartesian plane.
 * You start by placing a thin brush at the origin (0,0)(0,0) and then make N axis-aligned strokes
 * without lifting the brush off of the canvas. For the iith stroke, you'll move your brush Li
 * units from its current position in a direction indicated by the character Di, which is either
 * U (up), D (down), L (left), or R (right), while leaving behind a line segment of paint between the
 * brush's current and new positions. For example, if L1=5 and D1='L', you'll draw a
 * stroke between coordinates (0,0) and (−5,0), with your brush ending up at coordinates
 * (−5,0). Note that each stroke is either horizontal or vertical, and that each stroke
 * (after the first) begins where the previous stroke ended.
 * <p>
 * This painting is being marketed as a work of mathematical art, and its value is based on the number
 * of times a certain mathematical symbol appears in it −− specifically, the plus sign. A plus sign is
 * considered to be present at a certain position if and only if, for each of the 4 cardinal directions
 * (up, down, left, and right), there's paint leading from the point in that direction (or, vice versa,
 * leading to that point from that direction). Note that the paint from arbitrarily many strokes of your
 * brush might come together to form any given plus sign, and that at most one plus sign may be considered
 * to exist at any given position.
 * <p>
 * Determine the number of positions in the painting at which a plus sign is present.
 * <p>
 * <b>Constraints</b>
 * <p>
 * 2≤N≤2,000,000
 * <p>
 * 1 <= Li <= 1,000,000,000
 * <p>
 * Di∈{U, D, L, R}
 */
public class MathArt {

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point of(int x, int y) {
            return new Point(x, y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{x=" + x + ", y=" + y + '}';
        }

        @Override
        public int compareTo(Point o) {
            int compare = Integer.compare(x, o.x);
            if (compare == 0) {
                compare = Integer.compare(y, o.y);
            }
            return compare;
        }
    }

    static class Line implements Comparable<Line> {
        Point start;
        Point end;

        public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        static Line of(Point start, Point end) {
            return new Line(start, end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "Line{start=" + start + ", end=" + end + '}';
        }

        @Override
        public int compareTo(Line o) {
            int compare = start.compareTo(o.start);
            if (compare == 0) {
                compare = end.compareTo(o.end);
            }
            return compare;
        }
    }

    public long getPlusSignCount(int N, int[] L, String D) {
        char[] moves = D.toCharArray();
        assert L.length == moves.length;


        final Set<Line> vLines = new TreeSet<>();
        final Set<Line> hLines = new TreeSet<>();

        getLines(L, moves, vLines, hLines);

        long intersections = 0;

        for (Line currentLine : vLines) {
            intersections += hLines.stream()
                    .filter(line -> !isVertical(line) &&
                            ((line.start.y > currentLine.start.y && line.start.y < currentLine.end.y) ||
                                    (line.start.y < currentLine.start.y && line.start.y > currentLine.end.y)))
                    .count();
        }

        return intersections;
    }

    private static void getLines(int[] L, char[] moves, Set<Line> vLines, Set<Line> hLines) {
        Point currentPoint = Point.of(0, 0);
        for (int i = 0; i < L.length; i++) {
            switch (moves[i]) {
                case 'U':
                    Point newPointU = Point.of(currentPoint.x, currentPoint.y + L[i]);
                    vLines.add(Line.of(currentPoint, newPointU));
                    currentPoint = newPointU;
                    break;
                case 'D':
                    Point newPointD = Point.of(currentPoint.x, currentPoint.y - L[i]);
                    vLines.add(Line.of(currentPoint, newPointD));
                    currentPoint = newPointD;
                    break;
                case 'L':
                    Point newPointL = Point.of(currentPoint.x - L[i], currentPoint.y);
                    hLines.add(Line.of(currentPoint, newPointL));
                    currentPoint = newPointL;
                    break;
                case 'R':
                    Point newPointR = Point.of(currentPoint.x + L[i], currentPoint.y);
                    hLines.add(Line.of(currentPoint, newPointR));
                    currentPoint = newPointR;
                    break;
            }
        }
    }

    private static boolean isVertical(Line line) {
        Point start = line.start;
        Point end = line.end;

        return start.x == end.x;
    }
}
