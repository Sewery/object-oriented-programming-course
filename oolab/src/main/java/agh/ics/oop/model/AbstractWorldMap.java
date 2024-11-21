package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements WorldMap {
    protected List<MapChangeListener> mapChangeListeners;
    protected final HashMap<Vector2d,Animal> animalHashMap;
    protected final MapVisualizer mapVisualizer;
    public AbstractWorldMap() {
        this.animalHashMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
        this.mapChangeListeners = new ArrayList<>();
    }
    public abstract Boundary getCurrentBounds();
    @Override
    public void place(Animal animal) throws IncorrectPositionException{
        if(canMoveTo(animal.getPosition())){
            animalHashMap.put(animal.getPosition(), animal);
            mapChanged("Animal %s placed on %s.".formatted(animal,animal.getPosition()));
            return;
        }
        throw new IncorrectPositionException(animal.getPosition());
    }
    @Override
    public void move(Animal animal, MoveDirection direction) {
        var originalPosition = animal.getPosition();
        animal.move(direction,this);
        if(!originalPosition.equals(animal.getPosition())){
            animalHashMap.remove(originalPosition);
            animalHashMap.put(animal.getPosition(), animal);
            mapChanged("Animal %s moved from %s to %s".formatted(animal,originalPosition,animal.getPosition()));
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
    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return mapVisualizer.draw(currentBounds.leftLowerCorner(),currentBounds.rightUpperCorner());
    }
    public void subscribeMapChangeListener(MapChangeListener listener) {
        mapChangeListeners.add(listener);
    }
    public void unsubscribeMapChangeListener(MapChangeListener listener) {
        mapChangeListeners.remove(listener);
    }
    private void mapChanged(String message) {
        mapChangeListeners.forEach(listener->listener.mapChanged(this,message));
    }
}
