package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected final HashMap<Vector2d,Animal> animalHashMap;
    protected final MapVisualizer mapVisualizer;
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
    public WorldElement objectAt(Vector2d position) {
        return animalHashMap.get(position);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position);
    }
    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animalHashMap.values());
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
}
