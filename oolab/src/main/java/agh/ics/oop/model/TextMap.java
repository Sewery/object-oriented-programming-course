package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldNumberPositionMap<String,Integer> {
    private int size;
    private final Map<Integer,String> positionTextMap;

    public TextMap() {
        this.size = 0;
        positionTextMap = new HashMap<>();
    }

    @Override
    public boolean place(String animal) {
        if(positionTextMap.containsValue(animal)) {
            return false;
        }
        positionTextMap.put(size, animal);
        size++;
        return true;
    }

    @Override
    public void move(String animal, MoveDirection direction) {
        if(positionTextMap.containsValue(animal)) {
            switch(direction) {
                case FORWARD,BACKWARD:{
                    positionTextMap.keySet()
                            .stream()
                            .filter(key->positionTextMap.get(key).equals(animal))
                            .findFirst()
                            .ifPresent(pos->{
                                System.out.println(pos);
                                if(canMoveTo(pos-1)){
                                    var animal2 = positionTextMap.get(pos-1);
                                    positionTextMap.put(pos-1, animal);
                                    positionTextMap.put(pos, animal2);
                                }
                            });
                    break;
                }
                case LEFT,RIGHT:{
                    positionTextMap.keySet()
                            .stream()
                            .filter(key->positionTextMap.get(key).equals(animal))
                            .findFirst()
                            .ifPresent(pos->{
                                if(canMoveTo(pos+1)){
                                    var animal2 = positionTextMap.get(pos+1);
                                    positionTextMap.put(pos+1, animal);
                                    positionTextMap.put(pos, animal2);
                                }
                            });
                    break;
                }
            }
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return positionTextMap.containsKey(position);
    }

    @Override
    public String objectAt(Integer position) {
        return positionTextMap.get(position);
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position>=0 && position<size;
    }

    @Override
    public String toString() {
        return positionTextMap.toString();
    }
}
