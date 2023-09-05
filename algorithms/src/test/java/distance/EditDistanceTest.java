package distance;

import org.junit.jupiter.api.Test;

import static distance.EditDistance.OneEditApart;
import static distance.EditDistance.editDistance;
import static org.junit.jupiter.api.Assertions.*;

class EditDistanceTest {
    @Test
    void editDistanceOne() {
        assertFalse(OneEditApart("cat", "cat"));
        assertFalse(OneEditApart("cat", "dog"));
        assertTrue(OneEditApart("cat", "cats"));
        assertTrue(OneEditApart("cat", "cut"));
        assertTrue(OneEditApart("cat", "cast"));
        assertTrue(OneEditApart("cat", "at"));
        assertFalse(OneEditApart("cat", "act"));
        assertFalse(OneEditApart("cat", "scats"));
        assertTrue(OneEditApart("cat", "scat"));
    }

    @Test
    void editDistanceTest() {
        assertEquals(0, editDistance("cat", "cat"));
        assertEquals(3, editDistance("cat", "dog"));
        assertEquals(1, editDistance("cat", "cats"));
        assertEquals(1, editDistance("cat", "cut"));
        assertEquals(1, editDistance("cat", "cast"));
        assertEquals(1, editDistance("cat", "at"));
        assertEquals(2, editDistance("cat", "act"));
        assertEquals(2, editDistance("cat", "scats"));
        assertEquals(1, editDistance("cat", "scat"));
    }
}