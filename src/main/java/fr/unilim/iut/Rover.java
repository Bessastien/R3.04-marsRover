package fr.unilim.iut;

import java.util.List;

public class Rover {
    private Position position;
    private Directions direction;

    public Rover(Position position, Directions direction) {
        this.position = position;
        this.direction = direction;
    }

    public Rover() {
        this(new Position(0,0), Directions.NORTH);
    }

    public void move(List<Character> instructions) {
        for (Character instruction : instructions) {
            switch (instruction) {
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'F':
                    moveForward();
                    break;
                case 'B':
                    moveBackward();
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

    public void moveForward() {
        switch (direction) {
            case NORTH:
                position = new Position(position.getX(), position.getY() + 1);
                break;
            case WEST:
                position = new Position(position.getX() - 1, position.getY());
                break;
            case SOUTH:
                position = new Position(position.getX(), position.getY() - 1);
                break;
            case EAST:
                position = new Position(position.getX() + 1, position.getY());
                break;
        }
    }

    public void moveBackward() {
        switch (direction) {
            case NORTH:
                position = new Position(position.getX(), position.getY() - 1);
                break;
            case WEST:
                position = new Position(position.getX() + 1, position.getY());
                break;
            case SOUTH:
                position = new Position(position.getX(), position.getY() + 1);
                break;
            case EAST:
                position = new Position(position.getX() - 1, position.getY());
                break;
        }
    }



    public Position getPosition() {
        return position;
    }
    public Directions getDirection() {
        return direction;
    }
}
