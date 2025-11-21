package fr.unilim.iut;

import java.util.List;

public class Rover {
    private Position position;
    private Directions direction;
    private Grid grid;
    private String obstacleMessage = null;

    public Rover(Position position, Directions direction, Grid grid) {
        this.position = position;
        this.direction = direction;
        this.grid = grid;
    }

    public Rover(Position position, Directions direction) {
        this(position, direction, new Grid());
    }

    public Rover() {
        this(new Position(0,0), Directions.NORTH, new Grid());
    }

    public void move(List<Character> instructions) {
        obstacleMessage = null;
        for (Character instruction : instructions) {
            switch (instruction) {
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'F':
                    if (!moveForward()) {
                        return; // Arrêt de la séquence si obstacle rencontré
                    }
                    break;
                case 'B':
                    if (!moveBackward()) {
                        return; // Arrêt de la séquence si obstacle rencontré
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

    public void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Directions.WEST;
                break;
            case WEST:
                direction = Directions.SOUTH;
                break;
            case SOUTH:
                direction = Directions.EAST;
                break;
            case EAST:
                direction = Directions.NORTH;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Directions.EAST;
                break;
            case EAST:
                direction = Directions.SOUTH;
                break;
            case SOUTH:
                direction = Directions.WEST;
                break;
            case WEST:
                direction = Directions.NORTH;
                break;
        }
    }

    public boolean moveForward() {
        Position newPosition;
        switch (direction) {
            case NORTH:
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;
            case WEST:
                newPosition = new Position(position.getX() - 1, position.getY());
                break;
            case SOUTH:
                newPosition = new Position(position.getX(), position.getY() - 1);
                break;
            case EAST:
                newPosition = new Position(position.getX() + 1, position.getY());
                break;
            default:
                return false;
        }

        // Normaliser la position (wrapping)
        newPosition = grid.normalise(newPosition);

        // Vérifier s'il y a un obstacle
        if (grid.hasObstacle(newPosition)) {
            obstacleMessage = "Obstacle detected at position (" + newPosition.getX() + ", " + newPosition.getY() + ")";
            return false;
        }

        position = newPosition;
        return true;
    }

    public boolean moveBackward() {
        Position newPosition;
        switch (direction) {
            case NORTH:
                newPosition = new Position(position.getX(), position.getY() - 1);
                break;
            case WEST:
                newPosition = new Position(position.getX() + 1, position.getY());
                break;
            case SOUTH:
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;
            case EAST:
                newPosition = new Position(position.getX() - 1, position.getY());
                break;
            default:
                return false;
        }

        // Normaliser la position (wrapping)
        newPosition = grid.normalise(newPosition);

        // Vérifier s'il y a un obstacle
        if (grid.hasObstacle(newPosition)) {
            obstacleMessage = "Obstacle detected at position (" + newPosition.getX() + ", " + newPosition.getY() + ")";
            return false;
        }

        position = newPosition;
        return true;
    }

    public Position getPosition() {
        return position;
    }

    public Directions getDirection() {
        return direction;
    }

    public String getObstacleMessage() {
        return obstacleMessage;
    }

    public Grid getGrid() {
        return grid;
    }
}
