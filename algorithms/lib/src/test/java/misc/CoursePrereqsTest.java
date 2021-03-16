package misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CoursePrereqsTest {
    CoursePrereqs coursePrereqs = new CoursePrereqs();

    @Test
    void findCourseOrder() {
        assertArrayEquals(new int[]{0, 2, 1, 3}, coursePrereqs.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        assertArrayEquals(new int[]{1, 0}, coursePrereqs.findOrder(2, new int[][]{}));
        assertArrayEquals(new int[]{}, coursePrereqs.findOrder(3, new int[][]{{0, 2}, {1, 2}, {2, 0}}));
        assertArrayEquals(new int[]{}, coursePrereqs.findOrder(3, new int[][]{{1, 0}, {0, 1}, {2, 0}}));
        assertArrayEquals(new int[]{2, 0, 1}, coursePrereqs.findOrder(3, new int[][]{{1, 0}}));
        assertArrayEquals(new int[]{1, 0}, coursePrereqs.findOrder(2, new int[][]{{0, 1}}));
        assertArrayEquals(new int[]{}, coursePrereqs.findOrder(4, new int[][]{{2, 0}, {1, 0}, {3, 1}, {3, 2}, {1, 3}}));
    }
}