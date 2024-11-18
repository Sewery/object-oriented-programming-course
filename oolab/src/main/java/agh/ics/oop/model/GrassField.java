package agh.ics.oop.model;

import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap implements WorldMap{

    private final HashMap<Vector2d,Grass> grassHashMap;
    public GrassField(int filedSize){
        this.grassHashMap = new HashMap<>();
        var uniqueList = RandomPositionsGenerator.generateUniqueList(filedSize,(int)Math.sqrt(filedSize*10));
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
        super.upperRightBorder = new Vector2d(tempMaxX,tempMaxY);
        super.lowerLeftBorder = new Vector2d(tempMinX,tempMinY);
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return super.animalHashMap.containsKey(position) || grassHashMap.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(super.animalHashMap.containsKey(position)){
            return super.animalHashMap.get(position);
        }
        return grassHashMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) || (grassHashMap.containsKey(position) && !super.animalHashMap.containsKey(position));
    }
    @Override
    public List<WorldElement> getElements() {
        var elements = super.getElements();
        elements.addAll(grassHashMap.values());
        return elements;
    }
}
