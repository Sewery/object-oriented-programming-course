package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> convertToMoveDirections(String[] args) {
        var moveDirections = new ArrayList<MoveDirection>();
        for (String arg : args) {
            if (arg.equals("f")
                    || arg.equals("b")
                    || arg.equals("r")
                    || arg.equals("l")
            ) {
                moveDirections.add(switch (arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException("Invalid MoveDirection");
                });
            }

        }
        return moveDirections;
    }
}

