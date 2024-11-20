package agh.ics.oop.model;

import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap implements WorldMap{

    private final HashMap<Vector2d,Grass> grassHashMap;
    private Vector2d upperRightBorder;
    private Vector2d lowerLeftBorder;
    public GrassField(int filedSize){
        this.grassHashMap = new HashMap<>();
        var uniqueSet = RandomPositionsGenerator.generateUniqueSet(filedSize,(int)Math.sqrt(filedSize*10));
        int tempMaxX = Integer.MIN_VALUE;
        int tempMaxY = Integer.MIN_VALUE;
        int tempMinX = Integer.MAX_VALUE;
        int tempMinY = Integer.MAX_VALUE;
        for (var elem:uniqueSet) {
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
    public boolean isOccupied(Vector2d position) {
        return super.animalHashMap.containsKey(position) || grassHashMap.containsKey(position);
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
    @Override
    public String toString() {
        int tempMaxX = upperRightBorder.getX();
        int tempMaxY = upperRightBorder.getY();
        int tempMinX = lowerLeftBorder.getX();
        int tempMinY = lowerLeftBorder.getY();
        for(var elem:animalHashMap.values()){
            tempMaxX= Math.max(tempMaxX,elem.getPosition().getX());
            tempMaxY= Math.max(tempMaxY,elem.getPosition().getY());
            tempMinX= Math.min(tempMinX,elem.getPosition().getX());
            tempMinY= Math.min(tempMinY,elem.getPosition().getY());
        }
        this.upperRightBorder = new Vector2d(tempMaxX,tempMaxY);
        this.lowerLeftBorder = new Vector2d(tempMinX,tempMinY);
        return mapVisualizer.draw(lowerLeftBorder,upperRightBorder);
    }
}
