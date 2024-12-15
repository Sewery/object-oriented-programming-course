package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> movements;
    private final WorldMap worldMap;
    public Simulation(List<Vector2d> positions, List<MoveDirection> movements,WorldMap worldMap) {
        this.movements = movements;
        this.worldMap = worldMap;
        this.animals = new ArrayList<>();
        positions.forEach(position -> {
            try {
                animals.add(new Animal(position));
                worldMap.place(animals.getLast());
            } catch (IncorrectPositionException e) {
                System.err.println(e.getLocalizedMessage());
            }
        });
    }

    public void run(){
        int indexCurrentAnimal = 0;
        System.out.println(worldMap);
        for (var moveDirection : movements) {
            var currentAnimal = animals.get(indexCurrentAnimal);
            worldMap.move(currentAnimal,moveDirection);
            indexCurrentAnimal++;
            indexCurrentAnimal %= animals.size();
//            try {
//                Thread.sleep(500);
//            }catch(InterruptedException e){
//                System.err.println(e.getLocalizedMessage());
//                break;
//            }
        }

    }
    List<Animal> getAnimals(){
        return Collections.unmodifiableList(animals);
    }

}
