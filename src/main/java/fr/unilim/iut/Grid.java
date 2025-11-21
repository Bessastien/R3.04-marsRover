package fr.unilim.iut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    public static final int GRID_SIZE = 100;
    private HashMap<Position, Cell> cells = new HashMap<>();

    public Grid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells.put(
                        new Position(i, j),
                        new Cell(i, j)
                );
            }
        }
    }

    public Map<Position, Cell> getGrid() {
        return cells;
    }

    public Cell getCell(Position position) {
        return cells.get(position);
    }

    public List<Cell> getAllCells() {
        return new ArrayList<>(cells.values());
    }

    public void setObstacles(List<Position> positions) {
        for (Position position : positions) {
            Cell cell = getCell(position);
            if (cell != null) {
                cell.changeContainsObstacle();
            }
        }
    }
}
