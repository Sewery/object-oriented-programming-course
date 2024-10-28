package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] convertToMoveDirections(String[] userInput) {
        var moveDirections = new ArrayList<MoveDirection>();
        for (String element : userInput) {
            switch (element) {
                case "f" -> moveDirections.add(MoveDirection.FORWARD);
                case "b" -> moveDirections.add(MoveDirection.BACKWARD);
                case "r" -> moveDirections.add(MoveDirection.RIGHT);
                case "l" -> moveDirections.add(MoveDirection.LEFT);
            }

        }
        return moveDirections.toArray(MoveDirection[]::new);
    }
}

