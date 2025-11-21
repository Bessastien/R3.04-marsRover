package fr.unilim.iut;

public class Cell {
    private Position position;
    private boolean containsObstacle;
    private boolean containsRover;

    public Cell(int x, int y, boolean containsObstacle, boolean containsRover) {
        this.position = new Position(x, y);
        this.containsObstacle = containsObstacle;
        this.containsRover = containsRover;
    }

    public Cell(int x, int y) {
        this(x, y, false, false);
    }

    public Position getPosition() {
        return position;
    }

    public boolean containsObstacle() {
        return containsObstacle;
    }

    public boolean containsRover() {
        return containsRover;
    }

    public void changeContainsRover() {
        this.containsRover = !this.containsRover;
    }

    public void changeContainsObstacle() {
        this.containsObstacle = !this.containsObstacle;
    }
}
