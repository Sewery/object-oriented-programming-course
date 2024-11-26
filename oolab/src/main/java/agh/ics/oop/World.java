package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.List;

public class World {
    private static void run(String[] userInput) {
        try {

            var moveDirections = OptionsParser.convertToMoveDirections(userInput);
            for (var move : moveDirections) {
                switch (move) {
                    case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                    case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                    case RIGHT -> System.out.println("Zwierzak skreca w prawo");
                    case LEFT -> System.out.println("Zwierzak skreca w lewo");
                }
                ;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getLocalizedMessage());
        }
        //System.out.println(String.join(", ",args));
    }

    public static void main(String[] args) {
        String[] arg = {"f", "f", "f", "l", "r", "f","r","l","b","r","f"};
        List<MoveDirection> directions = OptionsParser.convertToMoveDirections(arg);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(8, 10));
        AbstractWorldMap grassField = new GrassField(12);
        AbstractWorldMap rectangularMap = new RectangularMap(12, 12);
        //Adding subscriber
        grassField.subscribeMapChangeListener(new ConsoleMapDisplay());
        rectangularMap.subscribeMapChangeListener(new ConsoleMapDisplay());
        var simulationEngine = new SimulationEngine(
                List.of(
                        new Simulation(positions, directions, grassField),
                        new Simulation(positions, directions, rectangularMap)
                )
        );
        //simulationEngine.runSync();
        simulationEngine.runAsync();
        System.out.println("System zakonczyl dzialanie");

    }
}
