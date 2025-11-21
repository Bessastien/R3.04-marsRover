package fr.unilim.iut;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test 1: Mouvements de base (F, B, L, R)
        System.out.println("=== Test 1: Mouvements de base ===");
        Grid grid1 = new Grid();
        Rover rover1 = new Rover(new Position(0, 0), Directions.NORTH, grid1);
        System.out.println("Position initiale: (" + rover1.getPosition().getX() + ", "
            + rover1.getPosition().getY() + "), Direction: " + rover1.getDirection());

        List<Character> instructions1 = Arrays.asList('F', 'F', 'R', 'F', 'F', 'L', 'B');
        rover1.move(instructions1);
        System.out.println("Position finale: (" + rover1.getPosition().getX() + ", "
            + rover1.getPosition().getY() + "), Direction: " + rover1.getDirection());

        // Test 2: Wrapping (bords de la grille)
        System.out.println("\n=== Test 2: Wrapping (planète sphérique) ===");
        Grid grid2 = new Grid();
        Rover rover2 = new Rover(new Position(0, 0), Directions.WEST, grid2);
        System.out.println("Position initiale: (" + rover2.getPosition().getX() + ", "
            + rover2.getPosition().getY() + "), Direction: " + rover2.getDirection());

        List<Character> instructions2 = Arrays.asList('F', 'F');
        rover2.move(instructions2);
        System.out.println("Position après wrapping: (" + rover2.getPosition().getX() + ", "
            + rover2.getPosition().getY() + ")");
        System.out.println("Le rover est passé du bord gauche (x=0) au bord droit (x=98)");

        // Test 3: Détection d'obstacles
        System.out.println("\n=== Test 3: Détection d'obstacles ===");
        Grid grid3 = new Grid();
        List<Position> obstacles = Arrays.asList(
            new Position(0, 3),
            new Position(5, 5)
        );
        grid3.setObstacles(obstacles);

        Rover rover3 = new Rover(new Position(0, 0), Directions.NORTH, grid3);
        System.out.println("Position initiale: (" + rover3.getPosition().getX() + ", "
            + rover3.getPosition().getY() + ")");
        System.out.println("Obstacles placés en: (0,3) et (5,5)");

        List<Character> instructions3 = Arrays.asList('F', 'F', 'F', 'F', 'F');
        rover3.move(instructions3);
        System.out.println("Position finale: (" + rover3.getPosition().getX() + ", "
            + rover3.getPosition().getY() + ")");

        if (rover3.getObstacleMessage() != null) {
            System.out.println("⚠️ ALERTE: " + rover3.getObstacleMessage());
            System.out.println("Position finale: (" + rover3.getPosition().getX() + ", " + rover3.getPosition().getY() + ")");
            System.out.println("Le rover s'est arrêté avant l'obstacle.");
        }

        // Test 4: Combinaison wrapping + obstacles
        System.out.println("\n=== Test 4: Combinaison wrapping + obstacles ===");
        Grid grid4 = new Grid();
        grid4.setObstacles(Arrays.asList(new Position(99, 50)));

        Rover rover4 = new Rover(new Position(98, 50), Directions.EAST, grid4);
        System.out.println("Position initiale: (" + rover4.getPosition().getX() + ", "
            + rover4.getPosition().getY() + "), Direction: " + rover4.getDirection());

        System.out.println("Obstacle placé en: (99,50)");

        List<Character> instructions4 = Arrays.asList('F', 'F');
        rover4.move(instructions4);
        System.out.println("Position finale: (" + rover4.getPosition().getX() + ", "
            + rover4.getPosition().getY() + ")");

        if (rover4.getObstacleMessage() != null) {
            System.out.println("⚠️ ALERTE: " + rover4.getObstacleMessage());
            System.out.println("Position finale: (" + rover4.getPosition().getX() + ", " + rover4.getPosition().getY() + ")");

        }
    }
}
