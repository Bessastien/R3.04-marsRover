package fr.unilim.iut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionsTest {

    @Test
    void testAllDirectionsExist() {
        assertNotNull(Directions.NORTH);
        assertNotNull(Directions.SOUTH);
        assertNotNull(Directions.EAST);
        assertNotNull(Directions.WEST);
    }

    @Test
    void testDirectionsValues() {
        Directions[] directions = Directions.values();
        assertEquals(4, directions.length);
    }

    @Test
    void testDirectionValueOf() {
        assertEquals(Directions.NORTH, Directions.valueOf("NORTH"));
        assertEquals(Directions.SOUTH, Directions.valueOf("SOUTH"));
        assertEquals(Directions.EAST, Directions.valueOf("EAST"));
        assertEquals(Directions.WEST, Directions.valueOf("WEST"));
    }
}

