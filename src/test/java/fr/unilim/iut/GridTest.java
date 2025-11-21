package fr.unilim.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid();
    }

    @Test
    void testGridCreation() {
        assertNotNull(grid);
        assertEquals(Grid.GRID_SIZE, 100);
    }

    @Test
    void testGetCell() {
        Position pos = new Position(5, 10);
        Cell cell = grid.getCell(pos);

        assertNotNull(cell);
        assertEquals(5, cell.getPosition().getX());
        assertEquals(10, cell.getPosition().getY());
    }

    @Test
    void testSetObstacles() {
        Position pos1 = new Position(5, 10);
        Position pos2 = new Position(20, 30);
        List<Position> obstacles = Arrays.asList(pos1, pos2);

        grid.setObstacles(obstacles);

        assertTrue(grid.hasObstacle(pos1));
        assertTrue(grid.hasObstacle(pos2));
    }

    @Test
    void testHasObstacle() {
        Position pos = new Position(15, 25);
        assertFalse(grid.hasObstacle(pos));

        grid.setObstacles(Arrays.asList(pos));
        assertTrue(grid.hasObstacle(pos));
    }

    @Test
    void testNormalisePositiveCoordinates() {
        Position pos = new Position(50, 60);
        Position normalized = grid.normalise(pos);

        assertEquals(50, normalized.getX());
        assertEquals(60, normalized.getY());
    }

    @Test
    void testNormaliseWrappingXNegative() {
        Position pos = new Position(-1, 50);
        Position normalized = grid.normalise(pos);

        assertEquals(99, normalized.getX());
        assertEquals(50, normalized.getY());
    }

    @Test
    void testNormaliseWrappingXPositive() {
        Position pos = new Position(100, 50);
        Position normalized = grid.normalise(pos);

        assertEquals(0, normalized.getX());
        assertEquals(50, normalized.getY());
    }

    @Test
    void testNormaliseWrappingYNegative() {
        Position pos = new Position(50, -1);
        Position normalized = grid.normalise(pos);

        assertEquals(50, normalized.getX());
        assertEquals(99, normalized.getY());
    }

    @Test
    void testNormaliseWrappingYPositive() {
        Position pos = new Position(50, 100);
        Position normalized = grid.normalise(pos);

        assertEquals(50, normalized.getX());
        assertEquals(0, normalized.getY());
    }

    @Test
    void testNormaliseWrappingBothAxes() {
        Position pos = new Position(-1, -1);
        Position normalized = grid.normalise(pos);

        assertEquals(99, normalized.getX());
        assertEquals(99, normalized.getY());
    }

    @Test
    void testNormaliseWrappingBothAxesPositive() {
        Position pos = new Position(100, 100);
        Position normalized = grid.normalise(pos);

        assertEquals(0, normalized.getX());
        assertEquals(0, normalized.getY());
    }

    @Test
    void testGetAllCells() {
        List<Cell> allCells = grid.getAllCells();

        assertNotNull(allCells);
        assertEquals(Grid.GRID_SIZE * Grid.GRID_SIZE, allCells.size());
    }
}

