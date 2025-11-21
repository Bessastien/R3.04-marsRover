package fr.unilim.iut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testCellCreationWithDefaults() {
        Cell cell = new Cell(5, 10);
        assertEquals(5, cell.getPosition().getX());
        assertEquals(10, cell.getPosition().getY());
        assertFalse(cell.containsObstacle());
        assertFalse(cell.containsRover());
    }

    @Test
    void testCellCreationWithObstacle() {
        Cell cell = new Cell(5, 10, true, false);
        assertTrue(cell.containsObstacle());
        assertFalse(cell.containsRover());
    }

    @Test
    void testCellCreationWithRover() {
        Cell cell = new Cell(5, 10, false, true);
        assertFalse(cell.containsObstacle());
        assertTrue(cell.containsRover());
    }

    @Test
    void testChangeContainsRover() {
        Cell cell = new Cell(5, 10);
        assertFalse(cell.containsRover());

        cell.changeContainsRover();
        assertTrue(cell.containsRover());

        cell.changeContainsRover();
        assertFalse(cell.containsRover());
    }

    @Test
    void testChangeContainsObstacle() {
        Cell cell = new Cell(5, 10);
        assertFalse(cell.containsObstacle());

        cell.changeContainsObstacle();
        assertTrue(cell.containsObstacle());

        cell.changeContainsObstacle();
        assertFalse(cell.containsObstacle());
    }
}

