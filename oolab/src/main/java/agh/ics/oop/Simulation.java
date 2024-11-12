package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.Collections;
import java.util.List;

public class Simulation<T,P> {

    private final List<T> entities;
    private final List<MoveDirection> movements;
    private final WorldMap<T,P> worldMap;
    public Simulation( List<MoveDirection> movements,WorldMap<T,P> worldMap, List<T> entities ) {
        this.movements = movements;
        this.worldMap = worldMap;
        this.entities = entities;
    }

    public void run() {
        int indexCurrentEntity = 0;
        System.out.println(worldMap);
        for (var moveDirection : movements) {
            var currentEntity = entities.get(indexCurrentEntity);
            worldMap.move(currentEntity,moveDirection);
            //System.out.printf("Zwierze %d : %s%n", indexCurrentEntity, currentEntity);
            System.out.println(worldMap);
            indexCurrentEntity++;
            indexCurrentEntity %= entities.size();
        }

    }
    List<T> getEntities(){
        return Collections.unmodifiableList(entities);
    }

}
