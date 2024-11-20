package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d,Animal> animalHashMap = new HashMap<>();
    private final Vector2d lowerLeftBorder;
    private final Vector2d upperRightBorder;
    public RectangularMap(int width, int height) {
        lowerLeftBorder = new Vector2d(0, 0);
        upperRightBorder = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position)
                && position.precedes(upperRightBorder)
                && position.follows(lowerLeftBorder);
    }

}
