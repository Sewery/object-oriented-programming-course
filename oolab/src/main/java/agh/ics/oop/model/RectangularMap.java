package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d,Animal> animalHashMap = new HashMap<>();
    public RectangularMap(int width, int height) {
        super.lowerLeftBorder = new Vector2d(0, 0);
        super.upperRightBorder = new Vector2d(width-1, height-1);
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalHashMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position)
                && position.precedes(super.upperRightBorder)
                && position.follows(super.lowerLeftBorder);
    }

}
