package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    private static void run(String[] userInput) {
        var moveDirections= OptionsParser.convertToMoveDirections(userInput);
        for (var move : moveDirections) {
            switch(move){
                case FORWARD-> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD-> System.out.println("Zwierzak idzie do tylu");
                case RIGHT-> System.out.println("Zwierzak skreca w prawo");
                case LEFT-> System.out.println("Zwierzak skreca w lewo");
            };
        }
        //System.out.println(String.join(", ",args));
    }
    public static void main(String[] args) {

        List<MoveDirection> directions = OptionsParser.convertToMoveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }
}
