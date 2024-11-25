package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap implements WorldMap{

    private final HashMap<Vector2d,Grass> grassHashMap;
    private Vector2d upperRightGrassBorder;
    private Vector2d lowerLeftGrassBorder;
    public GrassField(int filedSize){
        this.grassHashMap = new HashMap<>();
        var uniqueSet = RandomPositionsGenerator.generateUniqueSet(filedSize,(int)Math.sqrt(filedSize*10));
        this.upperRightGrassBorder = new Vector2d( Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.lowerLeftGrassBorder = new Vector2d( Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (var elem:uniqueSet) {
            grassHashMap.put(elem,new Grass(elem));
            this.lowerLeftGrassBorder = this.lowerLeftGrassBorder.lowerLeft(elem);
            this.upperRightGrassBorder = this.upperRightGrassBorder.upperRight(elem);
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return super.animalHashMap.containsKey(position) || grassHashMap.containsKey(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d upperRightBorder = upperRightGrassBorder;
        Vector2d lowerLeftBorder = lowerLeftGrassBorder;
        for (var elem:animalHashMap.keySet()) {
            lowerLeftBorder = lowerLeftBorder.lowerLeft(elem);
            upperRightBorder = upperRightBorder.upperRight(elem);
        }
        return new Boundary(lowerLeftBorder, upperRightBorder);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(super.animalHashMap.containsKey(position)){
            return super.objectAt(position);
        }
        return grassHashMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) || (grassHashMap.containsKey(position) && !super.animalHashMap.containsKey(position));
    }
    @Override
    public List<WorldElement> getElements() {
        var elements = super.getElements();
        elements.addAll(grassHashMap.values());
        return elements;
    }
}
