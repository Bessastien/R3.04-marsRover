package fr.unilim.iut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testPositionCreation() {
        Position position = new Position(5, 10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    void testPositionEquality() {
        Position pos1 = new Position(3, 7);
        Position pos2 = new Position(3, 7);
        Position pos3 = new Position(3, 8);

        assertEquals(pos1, pos2);
        assertNotEquals(pos1, pos3);
    }

    @Test
    void testPositionHashCode() {
        Position pos1 = new Position(3, 7);
        Position pos2 = new Position(3, 7);

        assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    void testPositionWithNegativeCoordinates() {
        Position position = new Position(-5, -10);
        assertEquals(-5, position.getX());
        assertEquals(-10, position.getY());
    }

    @Test
    void testPositionWithZeroCoordinates() {
        Position position = new Position(0, 0);
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }
}

