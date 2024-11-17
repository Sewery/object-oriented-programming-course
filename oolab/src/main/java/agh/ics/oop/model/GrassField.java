package agh.ics.oop.model;

import java.util.HashMap;

import static java.util.Collections.max;

public class GrassField implements WorldMap{

    private final HashMap<Vector2d,Grass> grassHashMap;
    private final HashMap<Vector2d,Animal> animalHashMap;
    private final MapVisualizer mapVisualizer;
    private final Vector2d upperRightBorder;
    private final Vector2d lowerLeftBorder;
    public GrassField(int filedSize){
        this.animalHashMap = new HashMap<>();
        this.grassHashMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
        var uniqueList = RandomVector2dGenerator.generateUniqueList(filedSize);
        int tempMaxX = Integer.MIN_VALUE;
        int tempMaxY = Integer.MIN_VALUE;
        int tempMinX = Integer.MAX_VALUE;
        int tempMinY = Integer.MAX_VALUE;
        for (var elem:uniqueList) {
            grassHashMap.put(elem,new Grass(elem));
            tempMaxX= Math.max(tempMaxX, elem.getX());
            tempMaxY= Math.max(tempMaxY,elem.getY());
            tempMinX= Math.min(tempMinX, elem.getX());
            tempMinY= Math.min(tempMinY,elem.getY());
        }
        this.upperRightBorder = new Vector2d(tempMaxX,tempMaxY);
        this.lowerLeftBorder = new Vector2d(tempMinX,tempMinY);
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
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position) || grassHashMap.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animalHashMap.containsKey(position)){
            return animalHashMap.get(position);
        }
        return grassHashMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || grassHashMap.containsKey(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeftBorder,upperRightBorder);
    }
}
