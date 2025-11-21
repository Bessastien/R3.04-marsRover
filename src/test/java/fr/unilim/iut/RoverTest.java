package fr.unilim.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    private Grid grid;
    private Rover rover;

    @BeforeEach
    void setUp() {
        grid = new Grid();
        rover = new Rover(new Position(0, 0), Directions.NORTH, grid);
    }

    // Tests de création
    @Test
    void testRoverCreationWithAllParameters() {
        Rover r = new Rover(new Position(5, 10), Directions.EAST, grid);
        assertEquals(5, r.getPosition().getX());
        assertEquals(10, r.getPosition().getY());
        assertEquals(Directions.EAST, r.getDirection());
    }

    @Test
    void testRoverCreationWithoutGrid() {
        Rover r = new Rover(new Position(5, 10), Directions.EAST);
        assertEquals(5, r.getPosition().getX());
        assertEquals(10, r.getPosition().getY());
        assertEquals(Directions.EAST, r.getDirection());
        assertNotNull(r.getGrid());
    }

    @Test
    void testRoverDefaultCreation() {
        Rover r = new Rover();
        assertEquals(0, r.getPosition().getX());
        assertEquals(0, r.getPosition().getY());
        assertEquals(Directions.NORTH, r.getDirection());
    }

    // Tests de rotation à gauche
    @Test
    void testTurnLeftFromNorth() {
        rover.turnLeft();
        assertEquals(Directions.WEST, rover.getDirection());
    }

    @Test
    void testTurnLeftFromWest() {
        rover = new Rover(new Position(0, 0), Directions.WEST, grid);
        rover.turnLeft();
        assertEquals(Directions.SOUTH, rover.getDirection());
    }

    @Test
    void testTurnLeftFromSouth() {
        rover = new Rover(new Position(0, 0), Directions.SOUTH, grid);
        rover.turnLeft();
        assertEquals(Directions.EAST, rover.getDirection());
    }

    @Test
    void testTurnLeftFromEast() {
        rover = new Rover(new Position(0, 0), Directions.EAST, grid);
        rover.turnLeft();
        assertEquals(Directions.NORTH, rover.getDirection());
    }

    // Tests de rotation à droite
    @Test
    void testTurnRightFromNorth() {
        rover.turnRight();
        assertEquals(Directions.EAST, rover.getDirection());
    }

    @Test
    void testTurnRightFromEast() {
        rover = new Rover(new Position(0, 0), Directions.EAST, grid);
        rover.turnRight();
        assertEquals(Directions.SOUTH, rover.getDirection());
    }

    @Test
    void testTurnRightFromSouth() {
        rover = new Rover(new Position(0, 0), Directions.SOUTH, grid);
        rover.turnRight();
        assertEquals(Directions.WEST, rover.getDirection());
    }

    @Test
    void testTurnRightFromWest() {
        rover = new Rover(new Position(0, 0), Directions.WEST, grid);
        rover.turnRight();
        assertEquals(Directions.NORTH, rover.getDirection());
    }

    // Tests de mouvement avant
    @Test
    void testMoveForwardNorth() {
        assertTrue(rover.moveForward());
        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
    }

    @Test
    void testMoveForwardEast() {
        rover = new Rover(new Position(0, 0), Directions.EAST, grid);
        assertTrue(rover.moveForward());
        assertEquals(1, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
    }

    @Test
    void testMoveForwardSouth() {
        rover = new Rover(new Position(5, 5), Directions.SOUTH, grid);
        assertTrue(rover.moveForward());
        assertEquals(5, rover.getPosition().getX());
        assertEquals(4, rover.getPosition().getY());
    }

    @Test
    void testMoveForwardWest() {
        rover = new Rover(new Position(5, 5), Directions.WEST, grid);
        assertTrue(rover.moveForward());
        assertEquals(4, rover.getPosition().getX());
        assertEquals(5, rover.getPosition().getY());
    }

    // Tests de mouvement arrière
    @Test
    void testMoveBackwardNorth() {
        rover = new Rover(new Position(5, 5), Directions.NORTH, grid);
        assertTrue(rover.moveBackward());
        assertEquals(5, rover.getPosition().getX());
        assertEquals(4, rover.getPosition().getY());
    }

    @Test
    void testMoveBackwardEast() {
        rover = new Rover(new Position(5, 5), Directions.EAST, grid);
        assertTrue(rover.moveBackward());
        assertEquals(4, rover.getPosition().getX());
        assertEquals(5, rover.getPosition().getY());
    }

    @Test
    void testMoveBackwardSouth() {
        rover = new Rover(new Position(5, 5), Directions.SOUTH, grid);
        assertTrue(rover.moveBackward());
        assertEquals(5, rover.getPosition().getX());
        assertEquals(6, rover.getPosition().getY());
    }

    @Test
    void testMoveBackwardWest() {
        rover = new Rover(new Position(5, 5), Directions.WEST, grid);
        assertTrue(rover.moveBackward());
        assertEquals(6, rover.getPosition().getX());
        assertEquals(5, rover.getPosition().getY());
    }

    // Tests de wrapping
    @Test
    void testWrappingNorthBorder() {
        rover = new Rover(new Position(50, 99), Directions.NORTH, grid);
        assertTrue(rover.moveForward());
        assertEquals(50, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
    }

    @Test
    void testWrappingSouthBorder() {
        rover = new Rover(new Position(50, 0), Directions.SOUTH, grid);
        assertTrue(rover.moveForward());
        assertEquals(50, rover.getPosition().getX());
        assertEquals(99, rover.getPosition().getY());
    }

    @Test
    void testWrappingEastBorder() {
        rover = new Rover(new Position(99, 50), Directions.EAST, grid);
        assertTrue(rover.moveForward());
        assertEquals(0, rover.getPosition().getX());
        assertEquals(50, rover.getPosition().getY());
    }

    @Test
    void testWrappingWestBorder() {
        rover = new Rover(new Position(0, 50), Directions.WEST, grid);
        assertTrue(rover.moveForward());
        assertEquals(99, rover.getPosition().getX());
        assertEquals(50, rover.getPosition().getY());
    }

    // Tests de détection d'obstacles
    @Test
    void testObstacleDetectionForward() {
        grid.setObstacles(Arrays.asList(new Position(0, 1)));

        assertFalse(rover.moveForward());
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertNotNull(rover.getObstacleMessage());
        assertTrue(rover.getObstacleMessage().contains("Obstacle detected"));
    }

    @Test
    void testObstacleDetectionBackward() {
        rover = new Rover(new Position(5, 5), Directions.NORTH, grid);
        grid.setObstacles(Arrays.asList(new Position(5, 4)));

        assertFalse(rover.moveBackward());
        assertEquals(5, rover.getPosition().getX());
        assertEquals(5, rover.getPosition().getY());
        assertNotNull(rover.getObstacleMessage());
    }

    @Test
    void testObstacleDetectionWithWrapping() {
        grid.setObstacles(Arrays.asList(new Position(0, 50)));
        rover = new Rover(new Position(99, 50), Directions.EAST, grid);

        assertFalse(rover.moveForward());
        assertEquals(99, rover.getPosition().getX());
        assertEquals(50, rover.getPosition().getY());
    }

    // Tests de séquences de commandes
    @Test
    void testMoveWithMultipleInstructions() {
        List<Character> instructions = Arrays.asList('F', 'F', 'R', 'F', 'F');
        rover.move(instructions);

        assertEquals(2, rover.getPosition().getX());
        assertEquals(2, rover.getPosition().getY());
        assertEquals(Directions.EAST, rover.getDirection());
    }

    @Test
    void testMoveWithRotations() {
        List<Character> instructions = Arrays.asList('L', 'L', 'L', 'L');
        rover.move(instructions);

        assertEquals(Directions.NORTH, rover.getDirection());
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
    }

    @Test
    void testMoveStopsOnObstacle() {
        grid.setObstacles(Arrays.asList(new Position(0, 2)));
        List<Character> instructions = Arrays.asList('F', 'F', 'F', 'F');

        rover.move(instructions);

        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
        assertNotNull(rover.getObstacleMessage());
    }

    @Test
    void testMoveWithBackwardCommands() {
        rover = new Rover(new Position(5, 5), Directions.NORTH, grid);
        List<Character> instructions = Arrays.asList('B', 'B', 'L', 'B');

        rover.move(instructions);

        assertEquals(6, rover.getPosition().getX());
        assertEquals(3, rover.getPosition().getY());
        assertEquals(Directions.WEST, rover.getDirection());
    }

    @Test
    void testComplexSequence() {
        List<Character> instructions = Arrays.asList('F', 'R', 'F', 'R', 'F', 'R', 'F');
        rover.move(instructions);

        // Le rover fait un carré et revient presque à sa position initiale
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(Directions.WEST, rover.getDirection());
    }

    @Test
    void testInvalidInstruction() {
        List<Character> instructions = Arrays.asList('X');

        assertThrows(IllegalArgumentException.class, () -> {
            rover.move(instructions);
        });
    }

    @Test
    void testObstacleMessageClearedOnNewMove() {
        grid.setObstacles(Arrays.asList(new Position(0, 1)));
        rover.moveForward();
        assertNotNull(rover.getObstacleMessage());

        rover.move(Arrays.asList('R'));
        assertNull(rover.getObstacleMessage());
    }

    @Test
    void testNavigationAroundObstacle() {
        // Obstacle devant
        grid.setObstacles(Arrays.asList(new Position(0, 1)));

        // Contourner l'obstacle
        List<Character> instructions = Arrays.asList('R', 'F', 'L', 'F', 'F', 'L', 'F');
        rover.move(instructions);

        assertEquals(0, rover.getPosition().getX());
        assertEquals(2, rover.getPosition().getY());
        assertNull(rover.getObstacleMessage());
    }

    @Test
    void testMultipleObstaclesInPath() {
        grid.setObstacles(Arrays.asList(
            new Position(0, 3),
            new Position(0, 5)
        ));

        List<Character> instructions = Arrays.asList('F', 'F', 'F', 'F', 'F', 'F');
        rover.move(instructions);

        // Le rover s'arrête avant le premier obstacle
        assertEquals(0, rover.getPosition().getX());
        assertEquals(2, rover.getPosition().getY());
        assertNotNull(rover.getObstacleMessage());
        assertTrue(rover.getObstacleMessage().contains("0, 3"));
    }
}

