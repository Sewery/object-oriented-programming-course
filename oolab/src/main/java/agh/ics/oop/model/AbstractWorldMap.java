package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final HashMap<Vector2d,Animal> animalHashMap;
    protected Vector2d upperRightBorder;
    protected Vector2d lowerLeftBorder;
    private final MapVisualizer mapVisualizer;
    public AbstractWorldMap() {
        this.animalHashMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            animalHashMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }
    @Override
    public void move(Animal animal, MoveDirection direction) {
        var originalPosition = animal.getPosition();
        animal.move(direction,this);
        if(!originalPosition.equals(animal.getPosition())){
            animalHashMap.remove(originalPosition);
            animalHashMap.put(animal.getPosition(), animal);
        }
    }
    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeftBorder, upperRightBorder);
    }
    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animalHashMap.values());
    }
}
