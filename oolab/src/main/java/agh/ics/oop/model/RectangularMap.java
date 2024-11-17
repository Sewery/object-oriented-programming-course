package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d,Animal> vector2dAnimalMap = new HashMap<>();
    private final Vector2d leftLowerCorner;
    private final Vector2d rightUpperCorner;
    private final MapVisualizer mapVisualizer;
    public RectangularMap(int width, int height) {
        this.leftLowerCorner = new Vector2d(0, 0);
        this.rightUpperCorner = new Vector2d(width-1, height-1);
        this.mapVisualizer = new MapVisualizer(this);
    }


    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            vector2dAnimalMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        var originalPosition = animal.getPosition();
        animal.move(direction,this);
        if(!originalPosition.equals(animal.getPosition())){
            vector2dAnimalMap.remove(originalPosition);
            vector2dAnimalMap.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return vector2dAnimalMap.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return vector2dAnimalMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position)
                && position.precedes(rightUpperCorner)
                && position.follows(leftLowerCorner);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(leftLowerCorner, rightUpperCorner);
    }
}
