package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d,Animal> vector2dAnimalMap = new HashMap<>();
    private final int width;
    private final int height;
    private final Vector2d leftLowerCorner;
    private final Vector2d rightUpperCorner;
    private final MapVisualizer mapVisualizer;
    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.leftLowerCorner = new Vector2d(0, 0);
        this.rightUpperCorner = new Vector2d(width, height);
        this.mapVisualizer = new MapVisualizer(this);
    }


    @Override
    public boolean place(Animal animal) {
        //When this animal was placed before i try to remove it to place it again if possible
        if(vector2dAnimalMap.containsValue(animal)){
            vector2dAnimalMap.keySet().stream()
                    .filter(key->vector2dAnimalMap.get(key)==animal) // I need to get actual object not the one with same value
                    .findFirst()
                    .ifPresent(pos->{
                        //I remove animal when is still on same spot or can move to the willing one
                        if(pos.equals(animal.getCurrPosition()) || canMoveTo(animal.getCurrPosition())){
                            vector2dAnimalMap.remove(pos, animal);
                        }
                    });
        }
        if(canMoveTo(animal.getCurrPosition())){
            vector2dAnimalMap.put(animal.getCurrPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animal.move(direction,this);
        place(animal);
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
        return !isOccupied(position) && position.precedes(rightUpperCorner) && position.follows(leftLowerCorner);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(leftLowerCorner, rightUpperCorner);
    }
}
